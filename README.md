# Projeto Compiladores

## Integrantes
- Gustavo Diaz Vicentin R.A.: 22.123.061-8
- Luiggi Paschoalini R.A.: 22.122.006-4
- Sérgio Martins de Oliveira Santos R.A.: 22.222.021-2


## Tokens

## Instruções de Execução

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

### Expressões Lógicas
```txt
bool maior = idade > 18;
bool valido = (idade >= 18) e (idade <= 65);
bool aprovado = (nota >= 7) ou (frequencia >= 75);
```

### Estruturas de Controle
```txt
se (idade >= 18) {
    txt status = "maior de idade";
} senaose (idade >= 12) {
    txt status = "adolescente";
} senao {
    txt status = "criança";
}

enquanto (contador < 10) {
    contador++;
}

para (int i = 0; i < 10; i++) {
    escreva(i);
}
```

### Funções
```txt
int func soma(int a, int b) {
    int resultado = a + b;
    retorna resultado;
}

dec func media(dec n1, dec n2) {
    dec resultado = (n1 + n2) / 2;
    retorna resultado;
}

txt func saudacao(txt nome) {
    txt mensagem = "Olá, " + nome;
    retorna mensagem;
}
```

### Programa Completo
```txt
int func fatorial(int n) {
    se (n <= 1) {
        retorna 1;
    }
    retorna n * fatorial(n - 1);
}

int func main() {
    int numero = 5;
    int resultado = fatorial(numero);
    escreva(resultado);
    retorna 0;
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

PARA -> "para" "(" DECLARACAO ";" EXPR_LOGICA ";" PARA_AUX ")" BLOCO_ENCAPSULADO

PARA_AUX ->  ATRIBUICAO | INCREMENTO


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
