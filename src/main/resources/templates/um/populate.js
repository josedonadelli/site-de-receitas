var mysql = require('mysql');  
var con = mysql.createConnection({  
host: "localhost:3306",  
user: "root",  
password: "root",  
database: "gft_palmirinha"  
});  
con.connect(function(err) {  
if (err) throw err;  
console.log("Connected!");  
var sql = "INSERT INTO unidade_medida (id, nome) VALUES ?";  
var values = [  
['2', 'colher de chá'],  
['3', 'xícara de café'],  
['4', 'colher de sopa']  
];  
con.query(sql, [values], function (err, result) {  
if (err) throw err;  
console.log("Number of records inserted: " + result.affectedRows);  
});  
});  