# Projeto Compiladores

## Integrantes
- Gustavo Diaz Vicentin R.A.: 22.123.061-8
- Luiggi Paschoalini R.A.: 22.122.006-4
- Sérgio Martins de Oliveira Santos R.A.: 22.222.021-2


## Tokens

## Instruções de Execução

### Pré-requisitos
- Java JDK 17 ou superior
- Maven (gerenciador de dependências)

### Compilação
1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/projeto-compiladores.git
cd projeto-compiladores
```

2. Compile o projeto:
```bash
javac *.java
```

### Execução
1. Para executar o compilador:
```bash
java Main
```


### Estrutura de Arquivos
  - `Lexico/` - Analisador léxico
  - `Sintatico/` - Analisador sintático
  - `Semantico/` - Analisador semântico
  - `codigo.txt`

### Faça seu código em codigo.txt

### Saída
O compilador irá gerar:
- Análise léxica: Lista de tokens encontrados
- Análise sintática: Árvore de derivação
- Análise semântica: Verificação de tipos e escopo
- Código intermediário (se implementado)

## Exemplos de código

### Declaração e Atribuição de Variáveis
```txt
int idade = 25;
dec altura = 1.75;
texto nome = "João";
bool ativo = verdade;
idade += 5;
contador++;
```

### Expressões Aritméticas
```txt
int resultado = (10 + 5) * 2;
dec media = (nota1 + nota2) / 2;
int total = valor1 * valor2 + valor3;
```

### Estruturas de Controle
```txt
se (idade >= 18) {
    texto status = "maior de idade";
} senaose (idade >= 12) {
    texto status = "adolescente";
} senao {
    texto status = "criança";
}

enquanto (contador < 10) {
    contador++;
}
```

### FUNCOES
```txt
def teste() {
  int b = 2;
}
```

## Gramatica
PROGRAMA -> BLOCO

BLOCO -> COMANDO BLOCO | ε

COMANDO -> DECISAO | DECLARACAO | REPETICAO | ATRIBUICAO | FUNCAO | INCREMENTO

### Elementos Basicos
ID -> [a-zA-Z][a-zA-Z0-9]* ✅

NUM -> [0-9]+ | [0-9]+.[0-9]+ ✅

BOOLEAN -> "verdade" | "mentira" ✅

TIPO -> "texto" | "bool" | "int" | "dec" ✅

### Valores e expressões
VALOR -> ID | NUM | BOOLEAN ✅

EXPRESSAO -> EXPR_ARITMETICA | EXPR_LOGICA | VALOR ✅

### Variaveis
DECLARACAO -> TIPO ID "=" EXPRESSAO ";" ✅

ATRIBUICAO -> ID OPERADOR_ATRIB EXPRESSAO ";" ✅

OPERADOR_ATRIB -> "=" | "+=" | "-=" ✅

INCREMENTO -> ID OPERADOR_INCREMENTO

OPERADOR_INCREMENTO -> "++" | "--"

### Expressões Aritméticas
OPERACAO_MATEMATICA ->  TERMO EXPR_MAT ✅
EXPR_MAT -> +TERMO EXPR_MAT | -TERMO EXPR_MAT | ε ✅

TERMO -> FATOR TERMO' ✅
TERMO' -> *FATOR TERMO' | / FATOR TERMO' | ε ✅

FATOR -> ID | NUM | (OPERACAO_MATEMATICA) ✅

### Expressões Lógicas
EXPRESSAO_LOGICA -> ID EXPRESSAO_LOGICA' | NUMERO EXPRESSAO_LOGICA' | BOOL EXPRESSAO_LOGICA'  | TEXTO EXPRESSAO_LOGICA'| (EXPRESSAO_LOGICA)

EXPRESSAO_LOGICA' -> OPERADOR_ARITIMETICO EXPRESSAO_LOGICA EXPRESSAO_LOGICA' | OPERADOR_RELACIONAL EXPRESSAO_LOGICA EXPRESSAO_LOGICA'| OPERADOR_LOGICO EXPRESSAO_LOGICA EXPRESSAO_LOGICA'

OPERADOR_ARITIMETICO -> "+" | "-" | "*" | "/"

OPERADOR_RELACIONAL -> ">" | "<" | "==" | "<=" | ">=" | "!="

OPERADOR_LOGICO -> "ou" | "e"

### Estruturas de controle

SE ->  "se" EXPR_LOGICA_ENCAPSULADA BLOCO_ENCAPSULADO SE_AUX ✅

SE_AUX -> "senaose" CONDICAO_ENCAPSULADA BLOCO_ENCAPSULADO SE_AUX | "senao" BLOCO_ENCAPSULADO | ε ✅

REPETICAO -> ENQUANTO | PARA

ENQUANTO -> "enquanto" EXPR_LOGICA_ENCAPSULADA BLOCO_ENCAPSULADO


### Funçoes

FUNCAO -> "fn" ID "(" PARAMETROS ")" "{" BLOCO RETORNO "}" ✅

PARAMETROS -> PARAMETRO | PARAMETRO "," PARAMETROS ✅

PARAMETRO -> TIPO ID | ε ✅

CHAMADA_FUNCAO -> ID "(" ARGUMENTOS ")"

ARGUMENTOS -> VALOR ARGUMENTO

ARGUMENTO -> "," ARGUMENTOS | ε

RETORNO -> "retorna" EXPRESSAO ";"

### Comandos de leitura e escrita

ESCREVA -> escreva(ARGUMENTOS);

EXECUTE -> "execute" "{" BLOCO "}" "enquanto" "(" EXPRESSAO_LOGICA ")"

## First and Follow Sets

| Rule | First Set | Follow Set |
|------|-----------|------------|
| PROGRAMA | {BLOCO} | {$} |
| BLOCO | {COMANDO, ε} | {EOF} |
| COMANDO | {DECISAO, DECLARACAO, REPETICAO, ATRIBUICAO, FUNCAO, INCREMENTO} | {COMANDO, EOF} |
| DECLARACAO | {TIPO} | {COMANDO, EOF} |
| ATRIBUICAO | {ID} | {COMANDO, EOF} |
| INCREMENTO | {ID} | {COMANDO, EOF} |
| TIPO | {texto, bool, int, dec} | {ID} |
| ID | {[a-zA-Z]} | {=, +=, -=, ++, --, ;, ), ,} |
| NUM | {[0-9]} | {;, ), ,, +, -, *, /, >, <, ==, <=, >=, !=} |
| BOOLEAN | {verdade, mentira} | {;, ), ,, >, <, ==, <=, >=, !=} |
| VALOR | {ID, NUM, BOOLEAN} | {;, ), ,, +, -, *, /, >, <, ==, <=, >=, !=} |
| EXPRESSAO | {ID, NUM, BOOLEAN, (} | {;, ), ,} |
| OPERACAO_MATEMATICA | {ID, NUM, (} | {;, ), ,, >, <, ==, <=, >=, !=} |
| EXPR_MAT | {+, -, ε} | {;, ), ,, >, <, ==, <=, >=, !=} |
| TERMO | {ID, NUM, (} | {;, ), ,, +, -, >, <, ==, <=, >=, !=} |
| TERMO' | {*, /, ε} | {;, ), ,, +, -, >, <, ==, <=, >=, !=} |
| FATOR | {ID, NUM, (} | {;, ), ,, +, -, *, /, >, <, ==, <=, >=, !=} |
| EXPRESSAO_LOGICA | {ID, NUM, BOOLEAN, (} | {;, ), ,} |
| EXPRESSAO_LOGICA' | {OPERADOR_ARITIMETICO, OPERADOR_RELACIONAL, OPERADOR_LOGICO, ε} | {;, ), ,} |
| OPERADOR_ARITIMETICO | {+, -, *, /} | {ID, NUM, BOOLEAN, (} |
| OPERADOR_RELACIONAL | {>, <, ==, <=, >=, !=} | {ID, NUM, BOOLEAN, (} |
| OPERADOR_LOGICO | {ou, e} | {ID, NUM, BOOLEAN, (} |
| SE | {se} | {COMANDO, EOF} |
| SE_AUX | {senaose, senao, ε} | {COMANDO, EOF} |
| REPETICAO | {enquanto, para} | {COMANDO, EOF} |
| ENQUANTO | {enquanto} | {COMANDO, EOF} |
| FUNCAO | {fn} | {COMANDO, EOF} |
| PARAMETROS | {TIPO, ε} | {)} |
| PARAMETRO | {TIPO, ε} | {,, )} |
| CHAMADA_FUNCAO | {ID} | {;, ), ,} |
| ARGUMENTOS | {VALOR} | {)} |
| ARGUMENTO | {,, ε} | {)} |
| RETORNO | {retorna} | {}} |
| ESCREVA | {escreva} | {COMANDO, EOF} |
| EXECUTE | {execute} | {COMANDO, EOF} |
