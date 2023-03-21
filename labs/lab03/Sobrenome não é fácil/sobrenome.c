#include <stdio.h>
#include <string.h>

int main() {
    int N, i;
    scanf("%d", &N);
    char nome[101];
    char sobrenome[101];
    for (i = 0; i < N; i++) {
        scanf(" %[^\n]s", nome); // Lê o nome completo, incluindo espaços em branco
        char *posicao = strrchr(nome, ' '); // Encontra a última ocorrência de um espaço em branco na string
        if (posicao == NULL) { // Se não houver espaço em branco, não há sobrenome
            strcpy(sobrenome, nome);
        } else {
            strcpy(sobrenome, posicao + 1); // Copia o sobrenome a partir da posição após o espaço em branco
        }
        printf("%s\n", sobrenome);
    }
    return 0;
}