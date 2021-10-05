# Challenge Mercadolibre

A continuacion s epuede ver el enunciado

Enunciado :
[link] (https://github.com/joaquinteromo7/xmenTest/blob/master/ejercicio/Challenge%20Mercadolibre%202021%20-%20Mutantes.pdf)

## Modo de uso

Contar con una App de test de API's ( Recomendado POSTMAN)

Por medio de la api /mutant/ se logra detectar si el ADN corresponde a un mutante o a un humano bajo la siguiente especificación:

Mutante: HTTP 200-OK, Humano: 403-Forbidden

### /mutante

Para hacer uso de la api /mutant se realiza una petición POST por medio la siguiente uRL

POST → https://challenge-mercadolibre-328121.uc.r.appspot.com/mutant

E ingresando en el body el siguiente Json con la siguiente estructura:

{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

Tal como se muestra a continuación 

![image](https://user-images.githubusercontent.com/91921828/136114343-0adf32b9-5256-49ca-8465-034ee93c093a.png)

No es necesario autenticarse la API se puede probar directamente.

### /stats

La api /stats devuelve un Json con la información correspondiente a la verificación realizada de ADN:

La estructura del Json devuelto es la siguiente : 

 {"count_mutant_dna":40, "count_human_dna":100, "ratio":0.4}
 
Donde count_mutant_dna es la cantidad de ADN mutantes detectados, count_human_dna es la cantidad de ADN humanos detectados y ratio es la relación entre la cantidad de ADN mutantes y la cantidad de ADN humnano.
 
Para hacer uso de la api /stats se realiza una peticion GET por medio la siguiente uRL

GET → https://challenge-mercadolibre-328121.uc.r.appspot.com/stats

Tal y como se muestra a continuación:

![image](https://user-images.githubusercontent.com/91921828/136115645-b52b56f4-5851-44d9-82d6-453a7ac81c39.png)

