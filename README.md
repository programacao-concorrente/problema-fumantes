# Problema dos Fumantes

1. Gerando o jar

$mvn clean compile assembly:single

2. Executando o jar 

$java -jar target/threads-1.0.0-jar-with-dependencies.jar 

# Descrição do problema dos fumantes com cigarros

No problema “Cigarrete Smokers” existem 4 processos envolvidos:

Agent, Smoker1, Smoker2 e Smoker3

Existem 3 ingredientes:  Tobacco, Paper e matches

Assumimos que o Agent tem recursos infinitos para os 3 ingredientes.

Cada smoker tem um suprimento infinito de cada um dos ingredientes.

Um smoker tem matches, outro o paper e o terceiro tem tobacco.

O agente escolhe 2 ingredientes diferentes de forma aleatória e disponibiliza na mesa de produção.

Dependendo de quais ingredientes sejam escolhidos o smoker complementa com seu ingrediente.

Smoker1 tem o tobacco.

Smoker2 tem o paper.

Smoker3 tem o match.

Exemplo:

1. O Agente coloca na mesa os ingredientes paper e match.

Smoker1 tem o tobacco, pega paper e match e faz o cigarro.

Smoker1 fuma.

Smoker1 avisa o agente para acordar.

2. O Agente coloca na mesa os ingredientes matches e tobacco.

Smoker2 tem o paper, pega matches e tobacco e faz o cigarro.

Smoker2 fuma.

Smoker2 avisa o agente para acordar.

3. O Agente coloca na mesa os ingredientes tobacco e paper.

Smoker3 tem o match, pega tobacco e paper e faz o cigarro

Smoker3 fuma

Smoker3 avisa o agente para acordar

Nesse caso o Agente representa um alocador de recursos.  Os smokers representam aplicações que precisam de recursos.
