Feature: Pesquisar produtos na Netshoes

  A funcionalidade permite que o usuário procure produtos no site da Netshoes

  @pesquisar-produto-nome
  Scenario Outline: Teste T01 - Pesquisar produto por nome
    Given o navegador está na página inicial da Netshoes
    When o usuário digita "<nome produto>" no campo de busca
    And clica no botão de lupa
    Then é exibida uma lista de produtos
    And o título da lista contém a palavra "<nome produto maiusculo>"

    Examples:
      | nome produto | nome produto maiusculo |
      | tenis        | TENIS                  |
      | chuteira     | CHUTEIRA               |
      | bolsa        | BOLSA                  |
      | agasalho     | AGASALHO               |

  @pesquisar-produto-menor-faixa-preco
  Scenario Outline: Teste T02 - Pesquisar produto pela menor faixa de preço
    Given os resultados de pesquisa da Netshoes para "<nome produto>" são exibidos
    When o usuário escolhe a menor faixa de preço
    Then é exibida uma lista de produtos
    And o preço do primeiro produto é maior ou igual ao valor inferior da faixa
    And o preço do primeiro produto é menor ou igual ao valor superior da faixa

    Examples:
      | nome produto |
      | tenis        |
      | chuteira     |
      | bolsa        |
      | agasalho     |
