# Proyecto Spring Boot - Gestión de Pedidos de Pizzas

Este proyecto es una aplicación de gestión de pedidos de pizzas utilizando Spring Boot. Permite crear pedidos, aplicar promociones como ser los días martes y miércoles las pizzas son 2x1 y los días jueves el delivery es gratis.

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener lo siguiente:

- **Java 11** o superior
- **Maven 3.6.3+**
- **IDE**: Spring Tool Suite (STS), IntelliJ IDEA, o cualquier otro IDE compatible con proyectos Spring Boot

## Configuración del proyecto

### Clonar el repositorio

Para obtener el código fuente del proyecto, primero clona el repositorio con el siguiente comando:

```bash
git clone https://github.com/Elsinho/NUR-Diplomado-M1T2.git
cd NUR-Diplomado-M1T2

```

### Ejecutar el programa
- Instalar el programa inicializandolo como maven: `mvn clean install`
    -Si al finalizar la ejecución del archivo pararece
    <br><br>
        [INFO] -----------------------------------------------------------<br>
        [INFO] BUILD SUCCESS<br>
        [INFO] -----------------------------------------------------------<br>

    El archivo ha compilado de manera satisfactoria y se puede proceder al siguiente paso, de lo contrario se revisa que error se esta presentando.
- Al ejecutar el comando pasado, se crea una carpera denomidada target, a la cual se navegara con `cd target`
- Dentro de esta carpeta se encontrará un archivo con el nombre: `[{nombre del programa}]-0.0.1-SNAPSHOT.jar`, este se procede a ejecutar con el comando: `java - jar [{nombre del programa}]-0.0.1-SNAPSHOT.jar`, el cual ejecutara el ambiente de java y correra los servicios en el puerto seleccionado, en este caso el puerto 8080.

### Obtener informaciones de los servicios.

#### Crear pizza predefinada

```
POST /api/pizzas/predefinidas
Accept: application/json
Content-Type: application/json

{
    "nombre": "Margarita",
    "ingredientes": [
        "Queso",
        "Tomate"
    ],
    "precio": 45.50
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/api/pizzas/predefinidas

{
    "nombre": "Margarita",
    "ingredientes": [
        "Queso",
        "Tomate"
    ],
    "precio": 45.50
}
```

#### Crear pizza personalizada

```
POST /api/pizzas/personalizada
Accept: application/json
Content-Type: application/json

{
    "nombre": "prueba1",
    "ingredientes": [
        "Queso",
        "Tomate",
        "jamón",
        "piña"
    ],
    "precio": 84.00
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/api/pizzas/personalizada

{
    "nombre": "prueba1",
    "ingredientes": [
        "Queso",
        "Tomate",
        "jamón",
        "piña"
    ],
    "precio": 84.00
}
```

#### Obtener pizzas predefinadas

```
GET /api/pizzas/predefinidas
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200 (OK)
Location header: http://localhost:8080/api/pizzas/personalizada

[
    {
        "nombre": "Margarita",
        "ingredientes": [
            "Queso",
            "Tomate"
        ],
        "precio": 45.50
    }
]

```

#### Crear pedido
- Pedido normal, el delivery por defecto el 5

```
POST /api/pedidos/crear
Accept: application/json
Content-Type: application/json

{
    "pizzaIds": [
        1
    ]
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/api/pedidos/crear

{
    "pizzas": [
        {
            "nombre": "Margarita",
            "ingredientes": [
                "Queso",
                "Tomate"
            ],
            "precio": 45.50
        }
    ],
    "fecha": "15/11/2024",
    "total": 50.50
}
```

- Pedido promoción 2x1

```
POST /api/pedidos/crear
Accept: application/json
Content-Type: application/json

{
    "pizzaIds": [
        1,1
    ]
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/api/pedidos/crear

{
    "pizzas": [
        {
            "nombre": "Margarita",
            "ingredientes": [
                "Queso",
                "Tomate"
            ],
            "precio": 45.50
        },
        {
            "nombre": "Margarita",
            "ingredientes": [
                "Queso",
                "Tomate"
            ],
            "precio": 45.50
        }
    ],
    "fecha": "13/11/2024",
    "total": 50.50
}
```

- Pedido promoción delivery gratis

```
POST /api/pedidos/crear
Accept: application/json
Content-Type: application/json

{
    "pizzaIds": [
        2
    ]
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/api/pedidos/crear

{
    "pizzas": [
        {
            "nombre": "prueba1",
            "ingredientes": [
                "Queso",
                "Tomate",
                "jamón",
                "piña"
            ],
            "precio": 84.00
        }
    ],
    "fecha": "14/11/2024",
    "total": 84.00
}
```

#### Control de excepciones

- No existe la pizza

```
POST /api/pedidos/crear
Accept: application/json
Content-Type: application/json

{
    "pizzaIds": [
        3
    ]
}

RESPONSE: HTTP 404 (Not Found)
Location header: http://localhost:8080/api/pedidos/crear

{
    "mensaje": "No existe la pizza"
}
```

- Elejir pizza

```
POST /api/pedidos/crear
Accept: application/json
Content-Type: application/json

{
    "pizzaIds": [
        
    ]
}

RESPONSE: HTTP 400 (Bad Request)
Location header: http://localhost:8080/api/pedidos/crear

{
    "mensaje": "El pedido debe contener al menos una pizza."
}
```

- Elejir la pizza del mismo precio para que aplique la promoción 2x1

```
POST /api/pedidos/crear
Accept: application/json
Content-Type: application/json

{
    "pizzaIds": [
        1,2
    ]
}

RESPONSE: HTTP 400 (Bad Request)
Location header: http://localhost:8080/api/pedidos/crear

{
    "mensaje": "Todas las pizzas deben tener el mismo precio para aplicar la promoción 2x1."
}
```

- Elejir 2 o par de pizza para que aplique la promoción 2x1

```
POST /api/pedidos/crear
Accept: application/json
Content-Type: application/json

{
    "pizzaIds": [
        1
    ]
}

RESPONSE: HTTP 400 (Bad Request)
Location header: http://localhost:8080/api/pedidos/crear

{
    "mensaje": "Debe elegir una pizza más para aplicar la promoción 2x1."
}
```
