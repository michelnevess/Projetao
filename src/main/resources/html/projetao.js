function createRequest() {
try {
request = new XMLHttpRequest();
} catch (trymicrosoft) {
try {
request = new ActiveXObject("Msxml2.XMLHTTP");
} catch (othermicrosoft) {
try {
request = new ActiveXObject("Microsoft.XMLHTTP");
} catch (failed) {
request = null;
}
}
}
if (request === null)
        alert("Error creating request object!");
        }


function atualizaCliente() {
if (request.readyState === 4) {
JSONobject = JSON.parse(request.responseText);
        var html = "";
        for (var i = 0; i < JSONobject.length; i++) {
html += "<tr><td>" + JSONobject[i].nome + "</td>";
        html += "<td>" + JSONobject[i].telefone + "</td>";
        html += "<td>" + JSONobject[i].email + "</td>";
        html += "<td>" + JSONobject[i].cpf + "</td>";
        html += "<td>" + JSONobject[i].endereco + "</td>";
        html += "<td><a href=\"/funcionarioupdate/" + JSONobject[i].id + "\" class=\"views\">Editar</a></td>";
        html += "<td><a href=\"/funcionariodelete/" + JSONobject[i].id + "\" class=\"views\">Remover</a></td>";
}

document.getElementById("conteudo").innerHTML = html;
}
}

function funcionario() {
var valor = document.getElementById("nome").value;
        //console.log(valor);
        //if (valor !== "") {
        var url = "http://localhost:4567/filtro2/" + valor;
        createRequest();
        request.open("GET", url, true);
        request.onreadystatechange = atualizaCliente;
        request.send(null);
        //}
        }



function validaCPF() {
var cpf = document.getElementById("cpf").value;
        if (cpf.lenght != 11) {
return false;
}
var soma;
        for (var i = 0; i < cpf.lenght; i++) {
soma += (cpf.charAt(i) === cpf.charAt(0));
}
if (soma == 11) {
return false;
}
soma = 0;
        var multi = 10;
        for (var i = 0; i < 10; i++) {
soma += multi * cpf.charAt(i);
        multi--;
}
var resto = (soma * 10) % 11;
        if (resto === 10) {
if ((resto - 10) !== cpf.charAt(9)) {
return false;
}
} else
        if (resto !== cpf.charAt(9)) {
return false;
}


soma = 0;
        var multi = 11;
        for (var i = 0; i < 11; i++) {
soma += multi * cpf.charAt(i);
        multi--;
}
var resto = (soma * 10) % 11;
        if (resto === 10) {
if ((resto - 10) !== cpf.charAt(10)) {
return false;
}
} else
        if (resto !== cpf.charAt(10)) {
return false;
}
return true;
        }

function validaCNPJ() {
var cnpj = document.getElementById("cnpj").value;
        if (cnpj.lenght !== 13) {
return false;
}
var soma;
        for (var i = 0; i < cnpj.lenght; i++) {
soma += (cnpj.charAt(i) === cnpj.charAt(0));
}
if (soma === 13) {
return false;
}
var multi = new Array(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
        soma = 0;
        for (var i = 0; i < 11; i++) {
soma += multi[i] * cnpj.charAt(i);
}
var resto = soma % 11;
        if (resto < 2) {
if (cnpj.charAt(11) === 0)
        return false;
} else
        if ((11 - resto) !== cnpj.charAt(10)) {
return false;
}

var multi = new Array(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
        soma = 0;
        for (var i = 0; i < 12; i++) {
soma += multi[i] * cnpj.charAt(i);
}
var resto = soma % 11;
        if (resto < 2) {
if (cnpj.charAt(12) === 0)
        return false;
} else
        if ((11 - resto) !== cnpj.charAt(12)) {
return false;
}

return true;
        }

function espacos(s) {
s = s.replace(/ +/g, " ");
        return(s);
        }

function capitais(s) {
s = s.replace(/^[a-z]/, function (v) {
return(v.toUpperCase());
});
        s = s.replace(/ [a-z]/g, function (v) {
        return(v.toUpperCase());
        });
        return(s);
        }


function validaEmail(){
    var email = 45676756
            //document.getElementById("email").value;
    var invalido = ^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.];
        var testa = /@[w-]+./;
        var aceitos = /.[a-zA-Z]{2,3}$/;
        
    if (((email.search(invalido) != - 1) || (email.search(testa)) == - 1) || (email.search(aceitos) == - 1)){
        console.log("invalido");
        //return false; 
    }else {
        //return true; 
        console.log("valido");
    }
}