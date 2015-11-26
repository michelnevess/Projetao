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

function ajaxfuncionario() {
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

function validaEstado() {
    var reg = /^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$/
    var nome = document.getElementById("estado").value;
    if (reg.test(nome)) {
        return true;
    }
    else {
        return false;
    }
}

function validaCidade() {
    var reg = /^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$/
    var nome = document.getElementById("cidade").value;
    if (reg.test(nome)) {
        return true;
    }
    else {
        return false;
    }
}

function validaBairro() {
    var reg = /^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$/
    var nome = document.getElementById("bairro").value;
    if (reg.test(nome)) {
        return true;
    }
    else {
        return false;
    }
}

function validaRua() {
    var reg = /^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+[0-9]*$/
    var nome = document.getElementById("rua").value;
    if (reg.test(nome)) {
        return true;
    }
    else {
        return false;
    }
}

function validaNumero() {
    var reg = /^[0-9]*$/
    var nome = document.getElementById("numero").value;
    if (reg.test(nome)) {
        return true;
    }
    else {
        return false;
    }
}

function adiciona() {
    if (request.readyState === 4) {
        JSONobject = JSON.parse(request.responseText);
        var quantidad = document.getElementById("tipos").value;
        console.log(quantidad);
        var html = "";
        for (var k = 0; k < quantidad; k++) {
            html+="<tr>";
            for (var i = 0; i < JSONobject.length; i++) {
                html += "<td>Peca: </td><td class=\"normal\"><select id=\"peca\" name=\"peca\">";
                html += "<option value=\"" + JSONobject[i].id + "\">" + JSONobject[i].nome + "</option></select></td>";
                html += "<td class=\"normal\">Unidade(s): </td><td class=\"normal\">";
                html += "<input type=\"text\" size=\"10\" id=\"unidade\" name=\"unidade\"></td>";
            }
            html+="</tr>";
            
        }
        document.getElementById("conteudo").innerHTML += html;
    }
}

function quantidade() {
    document.getElementById("conteudo").innerHTML = "";

    var url = "http://localhost:4567/lipeca";
    createRequest();
    request.open("GET", url, true);
    request.onreadystatechange = adiciona;
    request.send(null);


}

function validaData() {
    var dia1 = document.getElementById("dia1").value;
    var dia2 = document.getElementById("dia2").value;
    var mes1 = document.getElementById("mes1").value;
    var mes2 = document.getElementById("mes2").value;
    var ano1 = document.getElementById("ano1").value;
    var ano2 = document.getElementById("ano2").value;

    if (ano1 > ano2) {
        return false;
    }

    if (ano1 == ano2) {
        if (mes1 > mes2) {
            return false;
        } else {
            if (mes1 == mes2) {
                if (dia1 > dia2) {
                    return false;
                }
            }
        }
    }

    if (dia1 > 28 && mes1 == 2 && (ano1 % 4) != 0) {
        return false;
    }

    if (dia2 > 28 && mes2 == 2 && (ano2 % 4) != 0) {
        return false;
    }

    return true;
}