<h1># DogListApp</h1>
<h2>Arquitetura:</h2><br />
Para o Desenvolvimento foi utilizado o padrão MVVM com o auxilio do Android Jetpack(ViewModel, Room, LifecycleOwner) a imagem abaixo serve ara ilustrar a arquitura adotada: 

<img src="https://drive.google.com/file/d/18mL7CMM4blxpVDoVFeiukYNYOWWDf4UD/view?usp=sharing" heigth="150" width="150">

Além disso foi adotada para o desenvolvimento a utilização de uma arquitetura baseada em modularização (modulos ou bibliotecas no Androiod), ou seja, a aplicação foi divida em 3 modulos (serviços), são eles:
<p><b>local -> </b> responsavel por toda a camada que integra o banco de dados local e model com o auxilio da biblioteca Room.
<p><b>remote -></b> responsavel pela comunicação com a Api com o auxilio da biblioteca Retrofit; 
<p><b>app -></b> o modulo responsável pela parte das Views, estando no topo da hierarquia por se comunicar com os 2 outros modulos. Esse modulo é o responsável por instanciar a aplicação, realizar a gerenciar as injeções de dependências com Koin, ViewModel que interagem com a camada de repositorio e com a camada da View.





<h2>Libs:</h2><br />

<p><b>material Design -></b> utilizada para dar um toque a mais no quesito visual do projeto, a lib da google possui alguns componentes diferenciados e de facil customização, como por exemplo a tela de login que possui um edit text com um visual diferenciado e com apenas poucas linhas de código escritas no arquivo de styles;
<p><b>coroutines -></b> lib utilizada para chamadas async tanto para o banco de dados local como para as requisições remotas, além disso foi utilizada a lib para testes unitários;
<p><b>Koin -></b> Utilizado para injeção de dependencias entre os componentes, a lib é usada para injeção de depência de viewmodels, repositórios, SharedPreferences, Room, e testes unitários;
<p><b>Room -></b> lib do Android Jetpack para gerenciamento do banco de dados local responsável por armazenar os dados dos usuários;
<p><b>Glide -></b> Usado para carrengamento e caching de imagens;
<p><b>Lottie -></b> Usada para animação do loading, essa poderosa biblioteca da Airbnb é ótima e de fácil utilização, utilizei apenas para questão de estética;
<p><b>Zoomage -></b> utilizada para o efeito de preview semelhante ao preview de um aplicativo de galeria, seu ponto forte é a simplicidade e a rápida curva de aprendizado já que com poucos minutos de uso já consegui o meu objetivo para o projeto;
<p><b>Gson -></b> Utilizado para converter os resultados das chamadas da API para objetos;
<p><b>Retrofit -></b> Utilizada para interação com a API do projeto;





<h2>Tests:</h2><br />

<p>Infelizmente não pude realizar os todos os testes necessários devido ao gerenciamento do tempo, com isso consegui apenas criar os testes da ViewModel responsável por realizar o login do usuário, a ressalva para os testes é que os mesmos foram realizados com a utilização de libs como KoinTest e Coroutine para testes de unidade;
O teste Instrumentado segue a mesma premissa do teste de unidade, não consegui realizar todos os testes por questão de tempo, porém os testes realizados na tela de login cobrem exatamente todos os possiveis fluxos da tela, cobrindo as principais respostas de tratamento de erros e de sucesso!


<h3>Apk</h3>
O apk pode ser baixado do seguinte link: https://drive.google.com/file/d/1Zp5y5I7mzIiawG5RpjwR7oSdX2BOpYMt/view?usp=sharing
