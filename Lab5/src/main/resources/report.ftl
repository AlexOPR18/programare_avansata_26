<html>
<head>
    <title>Raport Biblioteca</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #dddddd; padding: 10px; text-align: left; }
        th { background-color: #4CAF50; color: white; }
    </style>
</head>
<body>
    <h1>Catalogul meu: ${numeCatalog}</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Titlu Resursa</th>
            <th>Locatie</th>
        </tr>
        <#-- Aici FreeMarker va pune automat randurile din Java -->
        <#list resurseleMele as item>
        <tr>
            <td>${item.id}</td>
            <td>${item.title}</td>
            <td>${item.location}</td>
        </tr>
        </#list>
    </table>
</body>
</html>