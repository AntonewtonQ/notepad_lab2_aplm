
# Ciclo de Vida das Atividades no Android

Este projeto demonstra o ciclo de vida das atividades em uma aplicação Android, incluindo os estados `onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onRestart` e `onDestroy`. Os estados são registrados pelo `StatusTracker`, e a classe `Utils` exibe essas mudanças de estado na interface gráfica.

## Sequência de Mensagens

### a) Início da Atividade

```plaintext
2024-10-16 10:14:19.081 19198-19198 MainActivity            ao.co.isptec.aplm.simpleactivity     I  onStart chamado
2024-10-16 10:14:19.136 19198-19198 MainActivity            ao.co.isptec.aplm.simpleactivity     I  onResume chamado
```

### b) Ao Clicar no Botão "Encerrar"

```
2024-10-16 10:17:08.491 19198-19198 MainActivity            ao.co.isptec.aplm.simpleactivity     I  onPause chamado
2024-10-16 10:17:12.626 19198-19198 VRI[MainActivity]       ao.co.isptec.aplm.simpleactivity     D  visibilityChanged oldVisibility=true newVisibility=false
2024-10-16 10:17:13.487 19198-19198 MainActivity            ao.co.isptec.aplm.simpleactivity     I  onStop chamado
2024-10-16 10:17:13.576 19198-19198 MainActivity            ao.co.isptec.aplm.simpleactivity     I  onDestroy chamado
```

Ao clicar em "Encerrar", a aplicação é pausada, parada e, por fim, o processo que a executa é destruído.

### c) Ao Clicar no Botão "HOME"

A aplicação é pausada e parada, mas o processo não é destruído, permanecendo em segundo plano.

### d) Ao Usar o Overview

A aplicação é reiniciada, chamando os métodos `onRestart`, `onStart`, e `onResume` em sequência.

## Interpretação da Saída Produzida pela Aplicação

O ciclo de vida das atividades é gerenciado automaticamente pelo sistema Android. O `StatusTracker` registra o estado atual de cada atividade, e as mudanças são exibidas na interface gráfica.

### Navegação Entre as Atividades

1. **Início da Atividade**  
   Ao iniciar a aplicação com `ActivityA`, os métodos `onCreate`, `onStart` e `onResume` são chamados, atualizando o estado para `created`, `started` e `resumed`, respectivamente.

2. **Navegação para Outra Atividade**  
   Ao navegar de `ActivityA` para `ActivityB`, `ActivityA` chama `onPause` e possivelmente `onStop`. `ActivityB` passa por `onCreate`, `onStart` e `onResume`.

3. **Navegação para uma Atividade de Diálogo**  
   Quando `DialogActivity` é aberta, a atividade anterior chama `onPause`, mas não `onStop`, pois o diálogo não ocupa a tela inteira. Ao fechar o diálogo, o método `onResume` é chamado.

4. **Fechamento de uma Atividade**  
   Ao fechar uma atividade, `onDestroy` é chamado, e o estado é atualizado para `destroyed`.

## Comportamento Esperado

- **Transições Suaves**: O sistema Android gerencia automaticamente os métodos do ciclo de vida durante a navegação.
- **Logs de Ciclo de Vida**: O `StatusTracker` e `Utils.printStatus()` registram os eventos do ciclo de vida das atividades.
- **Diálogos**: Quando `DialogActivity` é aberta, a atividade subjacente entra em `onPause`, mas não em `onStop`.

## Wireframe das Atividades

Abaixo está um wireframe representando as transições entre as atividades na aplicação.

```
+-------------+        +-------------+        +-------------+
|  ActivityA  | -----> |  ActivityB  | -----> |  ActivityC  |
+-------------+        +-------------+        +-------------+
       |                     |                      |
       |                     |                      |
       v                     v                      v
+-------------+        +-------------+        +-------------+
| DialogActivity |<----| DialogActivity |<----| DialogActivity |
+-------------+        +-------------+        +-------------+
```

### Legenda

- As setas representam as transições ao iniciar outra atividade.
- O diálogo pode ser aberto a partir de qualquer atividade principal.
- As setas duplas indicam que o usuário pode retornar à atividade anterior ao fechar o diálogo.

---

Esse README fornece uma visão clara e detalhada sobre o comportamento do ciclo de vida das atividades no projeto, facilitando o entendimento e uso para desenvolvedores interessados no funcionamento das atividades no Android.
