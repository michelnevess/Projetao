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


function atualizaFuncionario() {
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
    var url = "http://localhost:4567/filtro2/" + valor;
    createRequest();
    request.open("GET", url, true);
    request.onreadystatechange = atualizaFuncionario;
    request.send(null);

}

function resultadoCnpj() {
    if (request.readyState === 4) {
        var teste = request.responseText;
        var html = "";
        if (validaCNPJ() == false || teste == false) {
            html = "CNPJ Invalido";
        } else {
            html = "CNPJ Valido";
        }

        document.getElementById("conteudo").innerHTML += html;
    }
}

function ajaxCNPJ(cnpj) {
    document.getElementById("conteudo").innerHTML = "";
    document.getElementById("conteudo").style.display = "none";
    if (cnpj != "") {
        document.getElementById("conteudo").style.display = "block";
        var url = "http://localhost:4567/filtro1/" + cnpj;
        createRequest();
        request.open("GET", url, true);
        request.onreadystatechange = resultadoCnpj;
        request.send(null);
    }

}



function validaCPF() {
    var cpf = document.getElementById("cpf").value;
    if (cpf.length !== 11) {
        return false;
    }
    var soma = 0;
    for (var i = 0; i < cpf.length; i++) {
        if (cpf.charAt(i) === cpf.charAt(0)) {
            soma++;
        }
    }
    if (soma === 11) {
        return false;
    }
    soma = 0;
    var multi = 10;
    for (var i = 0; i < 9; i++) {
        soma += multi * cpf.charAt(i);
        multi--;
    }

    var resto = (soma * 10) % 11;
    if (resto == 10) {
        if ((resto - 10) != cpf.charAt(9)) {
            return false;
        }
    } else if (resto != cpf.charAt(9)) {
        return false;
    }


    soma = 0;
    var multi = 11;
    for (var i = 0; i < 10; i++) {
        soma += multi * cpf.charAt(i);
        multi--;
    }
    var resto = (soma * 10) % 11;
    if (resto == 10) {
        if ((resto - 10) != cpf.charAt(10)) {
            return false;
        }
    } else
    if (resto != cpf.charAt(10)) {
        return false;
    }
    return true;
}

function validaCNPJ() {
    var cnpj = document.getElementById("cnpj").value;
    if (cnpj.length != 14) {
        return false;
    }
    var soma;
    for (var i = 0; i < cnpj.length; i++) {
        soma += (cnpj.charAt(i) == cnpj.charAt(0));
    }
    if (soma == 13) {
        return false;
    }
    var multi = new Array(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
    soma = 0;
    for (var i = 0; i < 12; i++) {
        soma += multi[i] * cnpj.charAt(i);
    }
    var resto = soma % 11;
    if (resto < 2) {
        if (cnpj.charAt(12) != 0) {
            return false;
        }
    } else
    if ((11 - resto) != cnpj.charAt(12)) {
        return false;
    }

    var multi = new Array(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
    soma = 0;
    for (var i = 0; i < 13; i++) {
        soma += multi[i] * cnpj.charAt(i);
    }
    var resto = soma % 11;
    if (resto < 2) {
        if (cnpj.charAt(13) != 0) {
            return false;
        }
    } else
    if ((11 - resto) != cnpj.charAt(13)) {
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


function validaEmail() {
    var email = document.getElementById("email").value;
    var reg = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
    if (reg.test(email)) {
        return true;
    }
    else {
        return false;
    }
}

function validaTelefone(telefone) {
    //var telefone = document.getElementById("telefone").value;
    var reg = /^[1-9]{2}[2-9][0-9]{7,8}$/
    if (reg.test(telefone)) {
        return true;
    }
    else {
        return false;
    }
}

function validaNome() {
    var reg = /^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$/
    var nome = document.getElementById("nome").value;
    if (reg.test(nome)) {
        return true;
    }
    else {
        return false;
    }
}

function validaQuantidade(quantidade) {
    //var quantidade = document.getElementsByName("quantidade");
    alert(quatidade);
    var reg = /[1-9]/
    //for (var i = 0; i < quantidade.length; i++) {
        for(var k = 0; k < quantidade[i]; k++){
            if(!reg.test(quantidade.charAt(k))){
                return false;
            }
        }
    //}
    return true;
}