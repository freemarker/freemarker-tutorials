<!doctype html>
<html lang="en">
    <head>
        <#-- this comes from HelloWorld.java, when we added
              model.addAttribute("pageTitle", "Example Freemarker Page"); -->
        <title>${pageTitle}</title>
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
        </div>
    </body>
</html>
