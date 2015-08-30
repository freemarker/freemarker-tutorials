<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Hello, world!</title>
        <link rel="stylesheet" type="text/css" href="${globals.staticPath}/css/styles.css">
    </head>
    <body>
        <div class="example-page">
            <h1>Hello, world!</h1>
            <p>If your UTF-8 encoding is set up properly, these elements should be the same:</p>
            <ul>
                <li>© | &copy; | &#169;</li>
                <li>® | &reg; | &#174;</li>
                <li>¥ | &yen; | &#165;</li>
            </ul>

            <p>This should have some Japanese text: 日本語 が 大好き！</p>

            <h2>Changing locale and timezone</h2>

            <h3>Default locale</h3>
            <@printLocaleAndTime />

            <h3>de_DE (German)</h3>
            <@changeLocale newLocale="de_DE" />

            <@printLocaleAndTime />

            <h3>ja_JP (Japanese)</h3>

            <@changeLocale newLocale="ja_JP" />
            <@changeTimeZone newTimezone="Asia/Tokyo" />

            <@printLocaleAndTime />
        </div>
        <script src="${globals.staticPath}/js/hello-world.js"></script>
    </body>
</html>

<#macro changeLocale newLocale>
    <#setting locale = newLocale>

    <p>(Changing the <code>locale</code> to "${newLocale}" using <code>&lt;#setting locale = "${newLocale}"&gt;</code>)</p>
</#macro>


<#macro changeTimeZone newTimezone>
    <#setting time_zone = newTimezone>

    <p>(Changing the <code>time_zone</code> to ${newTimezone} using <code>&lt;#setting time_zone = "${newTimezone}"&gt;</code>)
</#macro>

<#macro printLocaleAndTime>
    <p>The <code>locale</code> of this project is currently <strong>${.locale}</strong> and the current time is <strong>${.now?string.full}</strong></p>
</#macro>
