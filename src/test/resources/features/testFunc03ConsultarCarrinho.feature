Feature: Consultar carrinho de compra da Netshoes

  A funcionalidade permite que o usuário consulte seu carrinho de compra no site da Netshoes

  @consultar-carrinho-compra
  Scenario Outline: Teste T05 - Consultar valor total do carrinho de compra
    Given foi adicionado um "<tipo de bolsa>" ao carrinho de compra da Netshoes
    And o navegador está na página inicial da Netshoes
    When o usuário clica no carrinho de compra
    Then é exibida a lista de produtos adicionados ao carrinho de comra
    And o valor total do carrinho é maior que zero

    Examples:
      | tipo de bolsa |
      | bolsa         |
      | mochila       |
      | sacola        |
      | mala          |