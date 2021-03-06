<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Body Page</title>
    <link rel='shortcut icon' href='https://themes.getbootstrap.com/wp-content/themes/bootstrap-marketplace/assets/images/fav/favicon.ico'>
    <script src='/js/bootstrap/themes/assets/jquery.min.js'></script>
    <script src='/js/bootstrap/themes/assets/Chart.min.js'></script>
    <script src='/js/bootstrap/themes/assets/Chart.bundle.min.js'></script>
    <script src='/js/bootstrap/themes/assets/tether.min.js'></script>
    <script src='/js/bootstrap/themes/assets/popper.min.js'></script>
    <script src='/js/bootstrap/themes/assets/bootstrap.min.js'></script>
    <style type="text/css">
        img.wp-smiley,
        img.emoji {
            display: inline !important;
            border: none !important;
            box-shadow: none !important;
            height: 1em !important;
            width: 1em !important;
            margin: 0 .07em !important;
            vertical-align: -0.1em !important;
            background: none !important;
            padding: 0 !important;
        }
    </style>
    <link rel='stylesheet' id='select2-css'  href='//themes.getbootstrap.com/wp-content/plugins/woocommerce/assets/css/select2.css?ver=3.1.2' type='text/css' media='all' />
    <link rel='stylesheet' id='woocommerce-layout-css'  href='//themes.getbootstrap.com/wp-content/plugins/woocommerce/assets/css/woocommerce-layout.css?ver=3.1.2' type='text/css' media='all' />
    <link rel='stylesheet' id='woocommerce-smallscreen-css'  href='//themes.getbootstrap.com/wp-content/plugins/woocommerce/assets/css/woocommerce-smallscreen.css?ver=3.1.2' type='text/css' media='only screen and (max-width: 768px)' />
    <link rel='stylesheet' id='woocommerce-general-css'  href='//themes.getbootstrap.com/wp-content/plugins/woocommerce/assets/css/woocommerce.css?ver=3.1.2' type='text/css' media='all' />
    <link rel='stylesheet' id='dokan-fontawesome-css'  href='https://themes.getbootstrap.com/wp-content/plugins/dokan-lite/assets/vendors/font-awesome/font-awesome.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='dokan-select2-css-css'  href='https://themes.getbootstrap.com/wp-content/plugins/dokan-lite/assets/vendors/select2/select2.css' type='text/css' media='all' />
    <link rel='stylesheet' id='dokan-pro-style-css'  href='https://themes.getbootstrap.com/wp-content/plugins/dokan-pro/assets/css/style.css?ver=1576308080' type='text/css' media='all' />
    <link rel='stylesheet' id='dokan-social-style-css'  href='https://themes.getbootstrap.com/wp-content/plugins/dokan-pro/assets/css/jssocials.css?ver=1576308080' type='text/css' media='all' />
    <link rel='stylesheet' id='dokan-social-theme-flat-css'  href='https://themes.getbootstrap.com/wp-content/plugins/dokan-pro/assets/css/jssocials-theme-flat.css?ver=1576308080' type='text/css' media='all' />
    <link rel='stylesheet' id='buttons-css'  href='https://themes.getbootstrap.com/wp-includes/css/buttons.min.css?ver=4.8.4' type='text/css' media='all' />
    <link rel='stylesheet' id='dashicons-css'  href='https://themes.getbootstrap.com/wp-includes/css/dashicons.min.css?ver=4.8.4' type='text/css' media='all' />
    <link rel='stylesheet' id='mediaelement-css'  href='https://themes.getbootstrap.com/wp-includes/js/mediaelement/mediaelementplayer.min.css?ver=2.22.0' type='text/css' media='all' />
    <link rel='stylesheet' id='wp-mediaelement-css'  href='https://themes.getbootstrap.com/wp-includes/js/mediaelement/wp-mediaelement.min.css?ver=4.8.4' type='text/css' media='all' />
    <link rel='stylesheet' id='media-views-css'  href='https://themes.getbootstrap.com/wp-includes/css/media-views.min.css?ver=4.8.4' type='text/css' media='all' />
    <link rel='stylesheet' id='imgareaselect-css'  href='https://themes.getbootstrap.com/wp-includes/js/imgareaselect/imgareaselect.css?ver=0.9.8' type='text/css' media='all' />
    <link rel='stylesheet' id='flexslider-css'  href='https://themes.getbootstrap.com/wp-content/themes/dokan/assets/css/flexslider.css' type='text/css' media='all' />
    <link rel='stylesheet' id='dokan-opensans-css'  href='https://fonts.googleapis.com/css?family=Open+Sans%3A400%2C700&#038;ver=4.8.4' type='text/css' media='all' />
    <link rel='stylesheet' id='dokan-theme-skin-css'  href='https://themes.getbootstrap.com/wp-content/themes/dokan/assets/css/skins/purple.css' type='text/css' media='all' />
    <link rel='https://api.w.org/' href='https://themes.getbootstrap.com/wp-json/' />
    <link rel="EditURI" type="application/rsd+xml" title="RSD" href="https://themes.getbootstrap.com/xmlrpc.php?rsd" />
    <link rel="wlwmanifest" type="application/wlwmanifest+xml" href="https://themes.getbootstrap.com/wp-includes/wlwmanifest.xml" />
    <link rel='shortlink' href='https://themes.getbootstrap.com/?p=7' />
    <link rel="alternate" type="application/json+oembed" href="https://themes.getbootstrap.com/wp-json/oembed/1.0/embed?url=https%3A%2F%2Fthemes.getbootstrap.com%2Fmy-account%2F" />
    <link rel="alternate" type="text/xml+oembed" href="https://themes.getbootstrap.com/wp-json/oembed/1.0/embed?url=https%3A%2F%2Fthemes.getbootstrap.com%2Fmy-account%2F&#038;format=xml" />
    <style>.woocommerce-password-strength.short {color: #e2401c}.woocommerce-password-strength.bad {color: #e2401c}.woocommerce-password-strength.good {color: #3d9cd2}.woocommerce-password-strength.strong {color: #0f834d}</style>	<noscript><style>.woocommerce-product-gallery{ opacity: 1 !important; }</style></noscript>
    <style type="text/css">.recentcomments a{display:inline !important;padding:0 !important;margin:0 !important;}</style>
    <style type="text/css">
    </style>
    <!-- The filemtime is to append a timestamp for the last time the stylesheet was updated to automate cache busting from CloudFlare -->
    <link rel='stylesheet' href='https://themes.getbootstrap.com/wp-content/themes/bootstrap-marketplace/style.css?ver=1553641463' />
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container">
            <a href="" class="navbar-brand" href="/">Sample Web</a>
            <div class="d-flex ml-auto">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#globalNavbar" aria-controls="globalNavbar" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="globalNavbar">
                <ul class="navbar-nav d-none d-lg-flex ml-2 order-3">
                    <li class="nav-item"><a class="nav-link" href="/web/signin/">Log Out</a></li>
                    <li class="nav-item">Welcome ${userName}</li>
                </ul>
                <ul class="navbar-nav d-lg-none">
                    <li class="nav-item-divider"></li>
                    <li class="nav-item"><a class="nav-link" href="/web/signin">Log Out</a></li>
                    <li class="nav-item">Welcome ${userName}</li>
                </ul>
            </div>
        </div>
    </nav>
	<h1>Login Success Test</h1>
</body>
</html>