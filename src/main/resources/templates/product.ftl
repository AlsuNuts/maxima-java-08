<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>
    <table>
        <tr>
            <th>ID</th>
            <th>${header_name}</th>
        </tr>
<#list products as product>
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
        </tr>
</#list>
    </table>
<br/><br/>
    <form>
        <h1>Add product</h1>
        <input name="id" placeholder="Product id ...">
    </form>
</body>
</html>