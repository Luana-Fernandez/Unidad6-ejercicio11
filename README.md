### **Gestión de productos**

#### **Escenario Happy Path: crear un producto correctamente**
```gherkin
Dado un producto con nombre Mouse
Y un precio de 1500
Cuando se guarda el producto
Entonces el sistema registra el producto
Y el producto aparece en la lista
```

#### **Escenario Happy Path: listado vacío**
```gherkin
Dado que no existen productos registrados
Cuando se solicita el listado
Entonces el sistema devuelve una lista vacía
```

#### **Escenario Happy Path: obtener lista de productos**
```gherkin
Dado que existen productos registrados
Cuando se solicita el listado
Entonces el sistema devuelve todos los productos
```

#### **Escenario Negative Path: crear un producto con precio negativo**
```gherkin
Dado un producto con nombre Teclado
Y un precio de -500
Cuando se intenta guardar el producto
Entonces el sistema rechaza la operación
Y muestra un mensaje de error
```

#### **Escenario Negative Path: crear un producto sin nombre**
```gherkin
Dado un producto con nombre vacío
Y un precio de 2500
Cuando se intenta guardar el producto
Entonces el sistema rechaza la operación
```

#### **Escenario Boundary Testing: crear un producto con precio cero**
```gherkin
Dado un producto con nombre Auriculares
Y un precio de 0
Cuando se guarda el producto
Entonces el sistema registra el producto
```

#### **Escenario Boundary Testing: nombre con un carácter**
```gherkin
Dado un producto con nombre A
Y un precio de 500
Cuando se guarda el producto
Entonces el sistema registra el producto correctamente
```
