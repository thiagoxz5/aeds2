#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
    int age;
    char name[22];
} Duende;

int compara(const void *a, const void *b) {
    Duende *a2 = (Duende *)a;
    Duende *b2 = (Duende *)b;    
    if (a2->age == b2-> age)
        return strcmp(a2->name, b2->name);
    return a2->age < b2->age;
}

int main() {
    int n, i, idade, times, k;
    char nome[22];
    Duende hue[30];
    scanf("%d ", &n);
    times = n/3;
    for (i = 0; i < n; i++) {
        scanf("%s %d", nome, &idade);
        strcpy(hue[i].name, nome);
        hue[i].age = idade;
    }
    qsort ((void *)&hue, n, sizeof(Duende), compara);
    for (i = 0; i < times; i++) {
        printf("Time %d\n", i + 1);
        for (k = i; k < n; k += times)
            printf("%s %d\n", hue[k].name, hue[k].age);
        printf("\n");
    }
    return 0;
}