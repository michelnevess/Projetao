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
        console.log(JSONobject.length);
        document.getElementById("conteudo").innerHTML = "";
        for (var i = 0; i < JSONobject.length; i++) {
            html += "<tr><td>" + JSONobject[i].nome + "</td>";
            html += "<td>" + JSONobject[i].telefone + "</td>";
            html += "<td>" + JSONobject[i].email + "</td>";
            html += "<td>" + JSONobject[i].cpf + "</td>";
            html += "<td>" + JSONobject[i].endereco.estado +"," + JSONobject[i].endereco.cidade +",";
            html += JSONobject[i].endereco.bairro +"," + JSONobject[i].endereco.rua +","+ JSONobject[i].endereco.numero +"," + JSONobject[i].endereco.complemento+ "</td>";
            html += "<td><a href=\"/funcionarioupdate/" + JSONobject[i].id + "\" class=\"views\">Editar</a></td>";
            html += "<td><a href=\"/funcionariodelete/" + JSONobject[i].id + "\" class=\"views\">Remover</a></td>";
        }

        document.getElementById("conteudo").innerHTML = html;
    }
}

function ajaxFuncionario() {
    var valor = document.getElementById("nome").value;
    if(valor!=""){
    var url = "http://localhost:4567/filtro2/" + valor;
    createRequest();
    request.open("GET", url, true);
    request.onreadystatechange = atualizaFuncionario;
    request.send(null);
    }

}

function resultadoCnpj() {
    if (request.readyState === 4) {
        var teste = request.responseText;
        var html = "";
        if (validaCNPJ() == true &&  teste == "false"){
            html = "CNPJ Valido";
        } else {
            html = "CNPJ Invalido";
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

function teste(cpf){
    var reg = /[0-9]{11}/;
    if(reg.test(cpf)){
        return true;
    }
    return false;
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

function validaTelefone() {
    var telefone = document.getElementById("telefone").value;
    var reg = /^[1-9]{2}[2-9][0-9]{7,8}$/;
    if (reg.test(telefone)) {
        return true;
    }
    else {
        return false;
    }
}

function validaNome(nome) {
    var reg = /^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$/;
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





function validaData() {
    var dia1 = document.getElementById("dia1").value;
    var dia2 = document.getElementById("dia2").value;
    var mes1 = document.getElementById("mes1").value;
    var mes2 = document.getElementById("mes2").value;
    var ano1 = document.getElementById("ano1").value;
    var ano2 = document.getElementById("ano2").value;

    if(ano1<2015){
        return false;
    }
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

function validaNum(num) {
    if(num==""){
        return false;
    }
    reg = /[0-9]+/;
    num = ""+num;
    for (var k = 0; k < num.length; k++) {
        if (!reg.test(num.charAt(k))) {
            return false;
        }
    }
    return true;
}

function validaFisico() {
    var str = "";

    if (!validaNome(document.getElementById("nome").value)) {
        str += "nome,";
    }
    if (!validaTelefone()) {
        str += "telefone,";
    }
    if (!validaEmail()) {
        str += "email,";
    }
    if (!validaCPF()) {
        str += "cpf,";
    }
    if (!validaNome(document.getElementById("estado").value)) {
        str += "estado,";
    }
    if (!validaNome(document.getElementById("cidade").value)) {
        str += "cidade,";
    }
    if (!validaNome(document.getElementById("bairro").value)) {
        str += "bairro,";
    }
    if (!validaRua()) {
        str += "rua,";
    }
    if (!validaNum(document.getElementById("numero").value)) {
        str += "numero,";
    }

    if (str != "") {
        alert("Campos invalidos: " + str);
    }else{
        document.form1.submit();
    }

}

function validaJuridico() {
    var str = "";

    if (!validaNome(document.getElementById("nome").value)) {
        str += "nome,";
    }
    if (!validaTelefone()) {
        str += "telefone,";
    }
    if (!validaEmail()) {
        str += "email,";
    }
    if (!validaCNPJ()) {
        str += "cnpj,";
    }
    if (!validaNome(document.getElementById("estado").value)) {
        str += "estado,";
    }
    if (!validaNome(document.getElementById("cidade").value)) {
        str += "cidade,";
    }
    if (!validaNome(document.getElementById("bairro").value)) {
        str += "bairro,";
    }
    if (!validaRua()) {
        str += "rua,";
    }
    if (!validaNum(document.getElementById("numero").value)) {
        str += "numero,";
    }

    if (str != "") {
        if (str.chartAt(str.length - 1) == ',') {
            str = str.substring(0, str.length - 2);
        }
        alert("Campos invalidos: " + str);
    }else{
        document.form1.submit();
    }

}

function validaValor(valor) {
    if(valor  == ""){
        return false;
    }
    var reg = /[0-9]*/
    if (reg.test(valor)) {
        return true;
    }
    return false;
}

function validaPeca(){ 
    var str = "";

    if (!validaNome(document.getElementById("nome").value)) {
        str += "nome,";
    }
    if (!validaValor(document.getElementById("valor").value)) {
        str += "valor,";
    }
    if (!validaNome(document.getElementById("fornecedor").value)) {
        str += "fornecedor,";
    }

    if (str != "") {
        if (str.chartAt(str.length - 1) == ',') {
            str = str.substring(0, str.length - 2);
        }
        alert("Campos invalidos: " + str);
    }

}

function validaPlaca() {
    var placa = document.getElementById("placa").value;
    var reg = /[A-Z]{3}-[0-9]{4}/;
    if (placa.length != 8) {
        return false;
    }

    if (reg.test(placa)) {
        return true;
    }
    return false;
}

function validaChassi() {
    var chassi = document.getElementById("chassi").value;
    var reg = /^[1-9a-zA-Z][^iIoOqQ\s!#=$%&¨'"´`^¹²³£¢¬§ºª()*+,-./:;°?@[\\\]_{|}~àáñçéèíìóòúùäëïöü]{12}[\d]{4}$/;
    if (reg.test(chassi)) {
        var soma = 0;
        var ultimo = "";
        for (var i = 0; i < chassi.length; i++) {
            if (ultimo == chassi.charAt(i)) {
                soma++;
            }
            ultimo = chassi.charAt(i);
        }
        if (soma > 5) {
            return false;
        }
        return true;
    }
    return false;
}


function validaAno() {
    var ano = document.getElementById("ano").value;
    if (ano.length != 4) {
        return false;
    }
    for (var i = 0; i < ano.length; i++) {
        if (ano.charAt(i) != 0 && ano.charAt(i) != 1 && ano.charAt(i) != 2 && ano.charAt(i) != 3 && ano.charAt(i) != 4 && ano.charAt(i) != 5 && 
                ano.charAt(i) != 6 && ano.charAt(i) != 7 && ano.charAt(i) != 8 && ano.charAt(i) != 9) {
            return false;
        }
    }
    return true;
}

function validaVeiculo() {
    var str = "";

    if (!validaNome(document.getElementById("modelo").value)) {
        str += "modelo,";
    }
    if (!validaNome(document.getElementById("marca").value)) {
        str += "marca,";
    }
    if (!validaAno()) {
        str += "ano,";
    }
    if (!validaChassi()) {
        str += "chassi,";
    }
    if (!validaPlaca()) {
        str += "placa,";
    }
    if (str != "") {
        alert("Campos invalidos: " + str);
    }else{
        document.form1.submit();
    }
}

function validaServico(){
    var str = "";
    if(!validaValor(document.getElementById("valor").value)){
        str += "valor,";
    }
    if(!validaNum(document.getElementById("unidade").value)){
        str += "unidade,";
    }
    if(!validaData()){
        str += "datas,";
    }
    if (str != "") {
        if (str.charAt(str.length - 1) === ',') {
            str = str.substring(0, str.length - 2);
        }
        alert("Campos invalidos: " + str);
    }else{
    document.form1.submit();
}
}
