#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>

typedef struct Personagem
{
	char name[100];
	int height;
	double weight;
	char colorHair[100];
	char skinColor[100];
	char eyeColor[100];
	char yearOfBirth[100];
	char gender[10];
	char homeworld[100];

} Personagem;

void setName(Personagem *p, char *name)
{
	strcpy(p->name, name);
}

char *getName(Personagem *p)
{
	return p->name;
}

void setHeight(Personagem *p, char *height)
{
	if (strstr(height, "unknown"))
	{
		p->height = 0;
	}
	else
	{
		p->height = atoi(height);
	}
}
int getHeight(Personagem *p)
{
	return p->height;
}

void setWeight(Personagem *p, char *weight)
{
	if (strstr(weight, "unknown"))
	{
		p->weight = 0;
	}
	else
	{
		p->weight = atof(weight);
	}
}

int getWeight(Personagem *p)
{
	return (int)p->weight;
}

void setColorHair(Personagem *p, char *colorHair)
{
	strcpy(p->colorHair, colorHair);
}

char *getColorHair(Personagem *p)
{
	return p->colorHair;
}

void setSkinColor(Personagem *p, char *skinColor)
{
	strcpy(p->skinColor, skinColor);
}

char *getSkinColor(Personagem *p)
{
	return p->skinColor;
}

void setEyeColor(Personagem *p, char *eyeColor)
{
	strcpy(p->eyeColor, eyeColor);
}

char *getEyeColor(Personagem *p)
{
	return p->eyeColor;
}

void setYearOfBirth(Personagem *p, char *yearOfBirth)
{
	strcpy(p->yearOfBirth, yearOfBirth);
}

char *getYearOfBirth(Personagem *p)
{
	return p->yearOfBirth;
}

void setGender(Personagem *p, char *gender)
{
	strcpy(p->gender, gender);
}

char *getGender(Personagem *p)
{
	return p->gender;
}

void setHomeworld(Personagem *p, char *homeworld)
{
	strcpy(p->homeworld, homeworld);
}

char *getHomeworld(Personagem *p)
{
	return p->homeworld;
}

//====================================================================//

void str_replace(char *str, const char *from, const char *to)
{
	int len_from = strlen(from);

	char *p;
	while ((p = strstr(str, from)) != NULL)
	{
		char buf[1024];
		strcpy(buf, p + len_from);

		*p = '\0';
		strcat(str, to);
		strcat(str, buf);
	}
}

//====================================================================//

void read_line(Personagem *p, char *line)
{
	//===================================================//

	str_replace(line, "{", "");
	str_replace(line, "}", "");
	str_replace(line, "': ", "");

	//===================================================//

	char *token;
	token = strtok(line, "[");

	//===================================================//

	char *token2;
	char attribute[100][100];
	int indexCount = 0;
	token2 = strtok(line, "'");
	while (token2 != NULL)
	{
		strcpy(attribute[indexCount++], token2);
		token2 = strtok(NULL, "'");
	}

	//===================================================//

	setName(p, attribute[1]);
	setHeight(p, attribute[4]);
	setWeight(p, attribute[7]);
	setColorHair(p, attribute[10]);
	setSkinColor(p, attribute[13]);
	setEyeColor(p, attribute[16]);
	setYearOfBirth(p, attribute[19]);
	setGender(p, attribute[22]);
	setHomeworld(p, attribute[25]);
}

//====================================================================//

void print_character(Personagem *p)
{
	printf(" ## %s ## ", p->name);
	printf("%d ## ", p->height);
	printf("%g ## ", p->weight);
	printf("%s ## ", p->colorHair);
	printf("%s ## ", p->skinColor);
	printf("%s ## ", p->eyeColor);
	printf("%s ## ", p->yearOfBirth);
	printf("%s ## ", p->gender);
	printf("%s ## ", p->homeworld);
	printf("\n");
}

//====================================================================//

bool isFim(char *s)
{
	if (strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M')
	{
		return true;
	}
	else
	{
		return false;
	}
}

//====================================================================//

int main(void)
{
	FILE *file;
	char json_txt[100][100];
	int count = 0;
	do
	{
		fgets(json_txt[count], 100, stdin);
		json_txt[count][strcspn(json_txt[count], "\n")] = '\0';
	} while (isFim(json_txt[count++]) == false);
	count--;

	char line[100][1000];
	Personagem p[100];

	for (int i = 0; i < count; i++)
	{
		file = fopen(json_txt[i], "r");
		if (file != NULL)
		{
			fgets(line[i], 1000, file);
			read_line(&p[i], line[i]);
			print_character(&p[i]);

			fclose(file);
		}
		else
		{
			printf("Failed to open file: %s\n", json_txt[i]);
		}
	}

	return 0;
}
