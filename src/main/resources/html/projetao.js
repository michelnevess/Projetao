var request = new XMLHttpRequest();


            function atualizaPagina() {
                if (request.state == 4 && request.state == 200) {
                    var respostaDoServidor = request.responseText;
                    var servico = respostaDoServidor.split('>');
                    resposta = JSON.parse(servico);
                    console.log(respostaDoServidor);
                    for(var i = 0; i < 6; i++){
                    console.log(servico[i]); 
                    
                    }
                    document.getElementById('div2').innerHTML = resposta;
                }
            }

            function fisico(valor) {
                
                console.log(valor);
                var url = "/fisico/"+valor;
                request.open("GET", url, true);
                request.onreadystatechange = atualizaPagina();
                request.send(null);
            }