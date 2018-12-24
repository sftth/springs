package sensing.framework.crypto;
import java.io.UnsupportedEncodingException;

public class Base64 {
	public static final String UTF_8 = "UTF-8";
	
	static final int CHUNK_SIZE = 76;
	
	static final byte[] CHUNK_SEPARATOR = {'\r', '\n'};
	
	private final int lineLength;
	
	private final byte[] lineSeparator;
	
	private final int encodeSize;
	
	private final int decodeSize;
	
	private final byte[] encodeTable;
	
	private byte[] buffer;
	
	private int pos;
	
	private int readPos;
	
	private int currentLinePos;
	
	private int modulus;
	
	private boolean eof;
	
	private int x;
	
	private static final int MASK_6BITS = 0x3f;
	
	private static final int MASK_8BITS = 0xff;
	
	private static final byte[] DECODE_TABLE = {
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54,
		55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
		5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
		24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34,
		35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51
	};
	
	private static final byte[] STANDARD_ENCODE_TABLE = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
	};
	
	private static final byte[] URL_SAFE_ENCODE_TABLE = {
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'
	};
	
	private static final byte PAD = '='	;
	
	private static final int DEFAULT_BUFFER_SIZE = 8192;
	
	private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
	
	private static IllegalStateException newIllegalStateException(String charsetName, UnsupportedEncodingException e) {
		return new IllegalStateException(charsetName + ": " + e);
	}
	
	public Base64() {
		this(false);
	}
	
	public Base64(boolean urlSafe) {
		this(CHUNK_SIZE, CHUNK_SEPARATOR, urlSafe);
	}
	
	public Base64(int lineLength) {
		this(lineLength, CHUNK_SEPARATOR);
	}
	
	public Base64(int lineLength, byte[] lineSeparator) {
		this(lineLength, lineSeparator, false);
	}
	
	public Base64(int lineLength, byte[] lineSeparator, boolean urlSafe) {
		if(lineSeparator == null) {
			lineLength = 0;
			lineSeparator = CHUNK_SEPARATOR;
		}
		this.lineLength = lineLength > 0 ? (lineLength / 4) * 4 : 0 ;
		this.lineSeparator = new byte[lineSeparator.length];
		System.arraycopy(lineSeparator, 0, this.lineSeparator, 0, lineSeparator.length);
		if(lineLength > 0 ) {
			this.encodeSize = 4 + lineSeparator.length;
		} else {
			this.encodeSize = 4;
		}
		
		this.decodeSize = this.encodeSize - 1;
		if(containsBase64Byte(lineSeparator)) {
			String sep = Base64.newStringUtf8(lineSeparator);
			throw new IllegalArgumentException("lineSeperator must not contain base64 charactoers: [" + sep + "]");
		}
		this.encodeTable = urlSafe ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
	}
	
	public static byte[] getBytesUtf8(String string) {
		return getBytesUnchecked(string, UTF_8);
	}
	
	public static byte[] getBytesUnchecked(String string, String charsetName) {
		if(string == null) {
			return null;
		}
		try {
			return string.getBytes(charsetName);
		} catch (UnsupportedEncodingException e) {
			throw newIllegalStateException(charsetName, e);
		}
	}
	
	public static byte[] encodeBase64(byte[] binaryData) {
		return encodeBase64(binaryData, false);
	}
	
	public static byte[] encodeBase64(byte[] binaryData, boolean isChunked) {
		return encodeBase64(binaryData, isChunked, false);
	}

	public static byte[] encodeBase64(byte[] binaryData, boolean isChunked, boolean urlSafe) {
		return encodeBase64(binaryData, isChunked, urlSafe, Integer.MAX_VALUE);
	}
	
	public static byte[] encodeBase64(byte[] binaryData, boolean isChunked, boolean urlSafe, int maxResultSize) {
		if(binaryData == null || binaryData.length == 0) {
			return binaryData;
		}
		
		long len = getEncodeLength(binaryData, CHUNK_SIZE, CHUNK_SEPARATOR);
		if(len > maxResultSize) {
			throw new IllegalArgumentException("Input array too big, the output array would be biiger(" +
					len + ") than the specified maxium size of " + maxResultSize);
		}
		
		Base64 b64 = isChunked ? new Base64(urlSafe) : new Base64(0, CHUNK_SEPARATOR, urlSafe);
		return b64.encodeBase64(binaryData);
	}	
	
	private static long getEncodeLength(byte[] pArray, int chunkSize, byte[] chunkSeparator) {
		chunkSize = (chunkSize / 4) * 4;
		
		long len = (pArray.length * 4) / 3;
		long mod = len % 4;
		if(mod != 0) {
			len += 4 - mod;
		}
		if(chunkSize > 0) {
			boolean lenChunksPerfectly = len % chunkSize == 0;
			len += (len/ chunkSize) * chunkSeparator.length;
			if(!lenChunksPerfectly) {
				len += chunkSeparator.length;
			}
		}
		
		return len;
	}
	
	private static boolean containBase64Byte(byte[] arrayOctet) {
		for(int i = 0; i < arrayOctet.length ; i++) {
			if(isBase64(arrayOctet[i])) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isBase64(byte octet) {
		return octet == PAD || (octet >= 0 && octet < DECODE_TABLE.length && DECODE_TABLE[octet] != -1);
	}
	
	public static String newStringUtf8(byte[] bytes) {
		return newString(bytes, UTF_8);
	}
	
	public static String newString(byte[] bytes, String charsetName) {
		if(bytes == null) {
			return null;
		}
		try {
			return new String(bytes, charsetName);
		} catch(UnsupportedEncodingException e) {
			throw newIllegalStateException(charsetName, e);
		}
	}
	
	private static boolean containsBase64Byte(byte[] arrayOctet) {
		for(int i = 0; i < arrayOctet.length ; i++) {
			if(isBase64(arrayOctet[i])) {
				return true;
			}
		}
		return false;
	}
	
	public static byte[] decodeBase64(String base64String) {
		return new Base64().decode(base64String);
	}
	
	
	public static byte[] decodeBase64(byte[] base64Data) {
		return new Base64().decode(base64Data);
	}
	public byte[] decode(String pArray) {
		return decode(Base64.getBytesUtf8(pArray));
	}
	
	public byte[] decode(byte[] pArray) {
		reset();
		if(pArray == null || pArray.length == 0) {
			return pArray;
		}
		long len = (pArray.length * 3) / 4;
		byte[] buf = new byte[(int) len];
		setInitialBuffer(buf, 0, buf.length);
		decode(pArray, 0, pArray.length);
		decode(pArray, 0, -1);
		
		byte[] result = new byte[pos];
		readResults(result, 0, result.length);
		return result;
	}
	
	private void reset() {
		buffer = null;
		pos = 0;
		readPos = 0;
		currentLinePos = 0;
		modulus = 0;
		eof = false;
	}
	
	private void setInitialBuffer(byte[] out, int outPos, int outAvail) {
		if(out != null && out.length == outAvail) {
			buffer = out;
			pos = outPos;
			readPos = outPos;
		}
	}
	
	private void decode(byte[] in, int inPos, int inAvail) {
		if(eof) {
			return;
		}
		if(inAvail < 0) {
			eof = true;
		}
		for(int i=0 ; i< inAvail; i++) {
			if(buffer == null || buffer.length - pos < decodeSize) {
				resizeBuffer();
			}
			byte b = in[inPos++];
			if(b == PAD) {
				eof = true;
				break;
			} else {
				if(b >= 0 && b < DECODE_TABLE.length) {
					int result = DECODE_TABLE[b];
					if(result >= 0 ) {
						modulus = (++modulus) % 4;
						x = (x << 6) + result;
						if(modulus == 0) {
							buffer[pos++] = (byte) ((x >> 16) & MASK_8BITS);
							buffer[pos++] = (byte) ((x >> 8) & MASK_8BITS);
							buffer[pos++] = (byte) (x & MASK_8BITS);
						}
					}
				}
			}
		}//for
		
		if(eof && modulus != 0) {
			x = x << 6;
			switch(modulus) {
			case 2 : 
				x = x << 6;
				buffer[pos++] = (byte) ((x >> 16) & MASK_8BITS);
				break;
			case 3 :
				buffer[pos++] = (byte) ((x >> 16) & MASK_8BITS);
				buffer[pos++] = (byte) ((x >> 8) & MASK_8BITS);
				break;
			}
		}
		
	}
	private void resizeBuffer() {
		if(buffer == null) {
			buffer = new byte[DEFAULT_BUFFER_SIZE];
			pos = 0;
			readPos = 0;
		} else {
			byte[] b = new byte[buffer.length * DEFAULT_BUFFER_RESIZE_FACTOR];
			System.arraycopy(buffer, 0, b, 0, buffer.length);
			buffer = b;
		}
	}
	
	int readResults(byte[] b, int bPos, int bAvail) {
		if(buffer != null) {
			int len = Math.min(avail(), bAvail);
			if(buffer != b) {
				System.arraycopy(buffer, readPos, b, bPos, len);
				readPos += len;
				if(readPos >= pos) {
					buffer = null;
				}
			}else {
				buffer = null;
			}
			return len;
		}
		
		return eof ? -1 : 0 ;
	}
	
	int avail() {
		return buffer != null ? pos - readPos : 0;
	}
}
