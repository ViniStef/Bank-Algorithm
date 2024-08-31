**ESTRUTURA DE DADOS E COMPLEXIDADE DE
ALGORITMOS**

Elaborado e Desenvolvido por:

**Marcos Vinícius Alves Goulart**

**Vinícius Steflitsch da Silva**

*Introdução:*

Cada grupo deverá implementar um algoritmo que determine o tempo médio que um
cliente permanece na fila de uma agência bancária. Quando um cliente entra na fila, o
horário é anotado. Quando ele sai, o tempo que ele permaneceu na fila é calculado e
adicionado ao tempo total de espera. Assim, no final do expediente, é possível determinar
quanto tempo, em média, cada cliente teve que aguardar para ser atendido.

*Objetivos:*

Ao final do expediente, caso ainda haja clientes na fila, eles devem ser atendidos. Quando
o expediente tiver terminado e não houver mais clientes na fila, as seguintes informações
devem ser impressas:
• Número total de clientes atendidos.
• Número de clientes que fizeram saque, depósito e pagamento.
• Tempo médio de espera na fila.
• Tempo extra de expediente.

*Ideação:*

O início do projeto teve com foco a criação de Classes, sendo estas:
Bank: Responsável pelo funcionamento do banco.
Client: Onde o objeto Cliente é instanciado.
ClientGeneration: Responsável pela criação de pelo auxílio de métodos os quais
compõem a Classe Client.
ServiceGeneration: Responsável pela criação dos métodos de Serviço, mantendo uma
conexão com Client.
DeskService: Classe responsável pelos Guiches do Banco.
Queue: É a Classe que faz instância da fila que vamos utilizar no Banco.

*Lógica:*

Iniciamos com um looping que usa o MathRandom para criar um número aleatório de 0 a
29, esta função ocorre durante todo funcionamento do banco, lembrando que, quando a
função retorna “0” o cliente era realmente instanciado.
Após a criação, dados importantes são armazenados do cliente, entre eles: Tempo de
Chegada e Tempo de Serviço ( este é classificado, pois teremos a quantidade de
transações feitas durante o expediente), ambos valores têm fundamental importância nas
contas para chegar ao objetivo proposto.Com estes dados, ocorria uma validação para
ver se o guichê estava previamente vazio, se sim o Cliente, mas especificamente falando
o valor de serviço dele era armazenado em um Vetor, este Vetor era o Desk que tinha 3
posições,
como seria supostamente o primeiro cliente que havia chegado, o tempo dele em
transação seria só o tempo de serviço.
Porém quando as 3 posições são completamente ocupados pelos Clientes, o próximo
que chega é redirecionado a fila, que é um ArrayList, a fila funciona como uma Lista, ou
seja, o primeiro a entrar é o primeiro a sair. Então baseando-se nisto fizemos um método
que retorna o menor valor sempre que os Guichês ficam cheios, este menor valor é
fundamental para que ocorra a exclusão do Cliente, pois a exclusão indica que ele saiu do
banco, com isso é possível atribuir o primeiro da fila a posição liberada. Contudo o
cálculo aqui muda um pouco, fazemos uma outra conta para saber quando que o guichê
estará livre novamente, este cálculo baseia-se no valor de saída do último Cliente (o
menor valor pego anteriormente) com a somatória do serviço do atual. Com isto é
possível alinhas a entrada e saída de clientes de maneira correta.
Com os Clientes previamente entrando e saindo do banco, criamos um conta que é
possível ter como retorno o Tempo de fila do Cliente:
Tempo fila = | (((TempoChega(i-1) + TempoFila(i-1) ) - tempoChega) + tempoServiço(i-1)) |
A conta retorna o Tempo de Fila de cada Cliente, e este é incrementado toda vez que
ocorre a criação de um cliente. Além de toda vez que criado, uma variável
“ClientNumber” é incrementada também, com isso, é possível atingir alguns dos
objetivos:
- Tempo Médio de Fila, que dividimos o “Tempo de Fila” pelo “ClientNumber”.
- Todos de Serviços de Maneira Separada(Armazenados após a criação do Cliente)
- Total de Clientes atendidos.
E agora falta mais um dos objetivos, o tempo extra de expediente, para alcançar este
objetivo foi proposto verificar a fila, uma vez que cheia os guichês não param, mas desta
maneira iria criar um looping infinito, porém após o tempo de trabalho chegar no limite
não é criado mais cliente, então somente os que chegaram previamente serão atendido.
Com isso após a fila estar vazia e o último cliente sair, armazenamos o dado de saída
deste último, com isso temos o tempo extra de expediente:
Tempo Extra: Tempo Total – Tempo Último Cliente;
deste modo concluímos o projeto.
