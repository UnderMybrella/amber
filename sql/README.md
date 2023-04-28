## amber-sql

### PostgreSQL
#### Code Generation
PostgreSQL keywords are generated from [here] using the following javascript:

```js
(() => {
    // Select rows from table_id
    var rows = document.querySelectorAll('div#KEYWORDS-TABLE table tr');
    // Construct json
    var json = [];
    for (var i = 1; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll('td, th');
        for (var j = 0; j < cols.length; j++) {
            row.push(cols[j].innerText)
        }
        json.push(row);
    }
    var json_string = JSON.stringify(json);
    // Download it
    var filename = 'postgresql_keywords.json';
    var link = document.createElement('a');
    link.style.display = 'none';
    link.setAttribute('target', '_blank');
    link.setAttribute('href', 'data:application/json;charset=utf-8,' + encodeURIComponent(json_string));
    link.setAttribute('download', filename);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
})();
```