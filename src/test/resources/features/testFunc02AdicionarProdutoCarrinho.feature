Feature: Adicionar produto ao carrinho de compra da Netshoes

  A funcionalidade permite que o usuário escolha um produto no site da Netshoes e adicione-o ao carrinho de compra

  @consultar-detalhe-produto
  Scenario Outline: Teste T03 - Consultar os detalhes do produto
    Given os resultados de pesquisa da Netshoes para "<nome produto>" são exibidos
    When o usuário clica no nome do primeiro produto da lista
    Then são exibidas as informações detalhadas do produto escolhido
    And a descrição contém o nome do produto escolhido

    Examples:
      | nome produto |
      | tenis        |
      | chuteira     |
      | bolsa        |
      | agasalho     |

  @nao-adicionar-calcado-carrinho
  Scenario Outline: Teste T04 - Adicionar calçado ao carrinho de compra sem escolher o tamanho
    Given são exibidas as informações detalhadas de um "<calcado>" da Netshoes
    When o usuário clica no botão comprar
    Then é exibida a mensagem Selecione o tamanho

    Examples:
      | calcado  |
      | tenis    |
      | chuteira |
      | chinelo  |
      | bota     |