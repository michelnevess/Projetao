var request = null;

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
                    var respostaDoServidor = request.responseText;
                    document.getElementsById("conteudo").innerXML = 
                            respostaDoServidor.getElementsByTagName("nome")[0].childNodes[0].nodeValue;
                    document.getElementsById("conteudo").innerXML = 
                            respostaDoServidor.getElementsByTagName("cpf")[0].childNodes[0].nodeValue;
                }
            }

            function fisico() {
                createRequest(); // cria objeto ajax
                var url = "http://localhost:4567/fisico"; // construindo a requisicao
                request.open("GET", url, true); // envia a requisicao
                request.onreadystatechange = atualizaPagina; // definindo funcao responsavel por tratar a resposta do servidor
                request.send(null);
            }