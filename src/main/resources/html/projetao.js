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


function atualizaPagina() {
    if (request.readyState === 4) {

        JSONobject = JSON.parse(request.responseText);
        console.log(JSONobject[0].cliente.nome);

        for (var i = 0; i < JSONobject.length; i++) {
            document.getElementById("conteudo").innerHTML += JSONobject[i];
        }
    }
}

function cliente(valor) {
    if (!valor == "") {
        var url = "http://localhost:4567/filtro1/" + valor;
        createRequest();
        request.open("GET", url, true);
        request.onreadystatechange = atualizaPagina;
        request.send(null);
    }
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