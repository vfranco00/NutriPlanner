<body>
    <h1>NutriPlanner – Sistema de Cardápio Semanal Inteligente</h1>
    <br>
    <h2>Funcionalidades Principais</h2>
    <ul>
        <li><strong>Cadastro e login de usuários</strong></li>
        <li><strong>Escolha de preferências alimentares básicas:</strong>
            <ul>
                <li>Vegano</li>
                <li>Vegetariano</li>
                <li>Sem lactose</li>
                <li>Sem glúten</li>
            </ul>
        </li>
        <li><strong>Geração automática de um cardápio semanal</strong> (café, almoço e jantar)</li>
        <li><strong>Visualização do cardápio gerado</strong></li>
        <li><strong>Geração de uma lista de compras</strong> baseada nas refeições da semana</li>
    </ul>
    <br>
    <h2>Estrutura de Pastas</h2>
    <p>Ainda está para decidir.</p>
    <br>
    <h2>Autores</h2>
    <p>Desenvolvido por Luana Neres e Victor Caetano</p>
    <br>
    <h2>Especificação dos Requisitos</h2>
    <h3>Requisitos Funcionais</h3>
    <ul>
        <li><strong>RF01 – Cadastro e Login de Usuários</strong>: O sistema deve permitir que o usuário se cadastre fornecendo nome e senha. O usuário pode fazer login para acessar o sistema, baseado nas credenciais cadastradas.</li>
        <li><strong>RF02 – Escolha de Preferências Alimentares</strong>: O sistema deve permitir ao usuário escolher suas preferências alimentares. Exemplo: Vegano, Vegetariano, Sem lactose, Sem glúten.</li>
        <li><strong>RF03 – Banco de Receitas</strong>: O sistema deve ter um banco de receitas pré-definido (não precisa de cadastro de novas receitas). As receitas devem ser categorizadas por tags como "vegano", "sem lactose", etc.</li>
        <li><strong>RF04 – Geração Automática de Cardápio</strong>: O sistema deve gerar automaticamente um cardápio semanal baseado nas preferências alimentares selecionadas. Cada dia da semana terá sugestões de café da manhã, almoço e jantar.</li>
        <li><strong>RF05 – Visualização do Cardápio</strong>: O usuário deve poder visualizar o cardápio gerado para a semana.</li>
        <li><strong>RF06 – Geração da Lista de Compras</strong>: O sistema deve gerar automaticamente uma lista de compras com base nas receitas do cardápio da semana. A lista de compras deve ser separada por categorias de itens (ex: hortifruti, laticínios, etc).</li>
    </ul>
    <h3>Requisitos Não Funcionais</h3>
    <ul>
        <li>Definição mediante andamento do projeto</li>
    </ul>
    <br>
    <h2>Modelo de Processos (Scrum)</h2>
    <h3>Sprint 1: Cadastro de Usuário e Preferências Alimentares</h3>
    <ul>
        <li><strong>Objetivo:</strong> Criar a base do sistema, com a funcionalidade de cadastro e login do usuário.</li>
        <li><strong>Tarefas:</strong>
            <ul>
                <li>Desenvolver a tela de login e cadastro.</li>
                <li>Poderá registrar e fazer login.</li>
                <li>Implementar o cadastro de preferências alimentares.</li>
            </ul>
        </li>
    </ul>
    <h3>Sprint 2: Banco de Receitas e Geração de Cardápio</h3>
    <ul>
        <li><strong>Objetivo:</strong> Implementar o banco de receitas e a geração do cardápio.</li>
        <li><strong>Tarefas:</strong>
            <ul>
                <li>Criar o banco de dados simples de receitas (em arquivos).</li>
                <li>Desenvolver a lógica para gerar o cardápio semanal com base nas preferências alimentares.</li>
                <li>Exibir o cardápio na interface do usuário.</li>
            </ul>
        </li>
    </ul>
    <h3>Sprint 3: Geração da Lista de Compras e Melhoria de UI</h3>
    <ul>
        <li><strong>Objetivo:</strong> Gerar a lista de compras e melhorar a interação com o usuário.</li>
        <li><strong>Tarefas:</strong>
            <ul>
                <li>Implementar a geração da lista de compras com base no cardápio.</li>
                <li>Organizar os itens por categorias (hortifruti, laticínios, etc).</li>
                <li>Melhorar a interface de visualização do cardápio e da lista de compras.</li>
            </ul>
        </li>
    </ul>
    <h3>Sprint 4: Testes e Polimento</h3>
    <ul>
        <li><strong>Objetivo:</strong> Garantir que o sistema esteja funcional e sem bugs.</li>
        <li><strong>Tarefas:</strong>
            <ul>
                <li>Realizar testes de funcionalidade (cardápio gerado corretamente, lista de compras correta).</li>
                <li>Corrigir erros de usabilidade.</li>
                <li>Melhorar a performance de geração de cardápio.</li>
            </ul>
        </li>
    </ul>
    <h3>Sprint 5: Documentação e Entrega</h3>
    <ul>
        <li><strong>Objetivo:</strong> Documentar o sistema e prepará-lo para a entrega final.</li>
        <li><strong>Tarefas:</strong>
            <ul>
                <li>Escrever a documentação do código e do funcionamento do sistema (README).</li>
                <li>Finalizar qualquer detalhe que falta na interface ou no processo.</li>
                <li>Entregar o sistema.</li>
            </ul>
        </li>
    </ul>
</body>
