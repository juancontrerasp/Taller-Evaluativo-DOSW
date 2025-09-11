# Sistema de Monitoreo de Stock

## Juan Pablo Contreras

---

## Descripción
Este proyecto implementa un sistema de monitoreo de stock en Java, diseñado para cumplir con los siguientes requisitos funcionales:
1. **Añadir Productos**: Permite agregar productos con nombre, precio, cantidad en stock y categoría.
2. **Modificar Stock**: Actualiza la cantidad disponible de un producto y notifica a los interesados.
3. **Notificar Cambios**: Notifica a dos agentes cuando el stock cambia:
   - **Agente 1 (LogAgent)**: Imprime en consola las unidades disponibles.
   - **Agente 2 (AlertAgent)**: Genera una alerta en consola si el stock es menor a 5 unidades.

El sistema está diseñado siguiendo los principios **SOLID** y utiliza el patrón de diseño **Observer** para manejar las notificaciones de cambios en el stock de manera desacoplada y extensible.

## Estructura del Proyecto
El proyecto está organizado en el paquete `edu.dosw.taller.Taller_Evaluativo_DOSW` y contiene las siguientes clases:

- **Product.java**: Representa un producto con atributos (nombre, precio, stock, categoría) y maneja la notificación a observadores cuando el stock cambia.
- **StockObserver.java**: Interfaz que define el contrato para los observadores, con el método `writeUpdate(Product)`.
- **AlertAgent.java**: Implementación de `StockObserver` que imprime una alerta si el stock es menor a 5.
- **LogAgent.java**: Implementación de `StockObserver` que imprime el stock actual de un producto.
- **Inventory.java**: Gestiona la colección de productos, permite añadir productos, modificar stock y consultar el inventario.

## Uso del Patrón Observer
El patrón **Observer** se implementa para notificar a los agentes (`AlertAgent` y `LogAgent`) cuando el stock de un producto cambia. Este patrón es ideal para este caso, ya que permite desacoplar la lógica de los productos de la lógica de notificación, facilitando la adición de nuevos agentes en el futuro sin modificar el código existente.

### Implementación del Patrón Observer
1. **Sujeto (Product)**:
   - La clase `Product` actúa como el sujeto observado.
   - Mantiene una lista de observadores (`ArrayList<StockObserver>`) y proporciona métodos para agregar observadores (`addObserver`) y notificarlos (`notifyObservers`).
   - Cuando se llama a `modifyStock`, actualiza el stock y ejecuta `notifyObservers`, que invoca el método `writeUpdate` de cada observador registrado, pasando el producto actualizado como parámetro.

2. **Observadores (AlertAgent y LogAgent)**:
   - La interfaz `StockObserver` define el método `writeUpdate(Product)` que todos los observadores deben implementar.
   - `LogAgent`: Imprime en consola el nombre del producto y su stock actual (e.g., "Laptop: 8 unidades").
   - `AlertAgent`: Verifica si el stock es menor a 5 y, si es así, imprime una alerta (e.g., "ALERTA: Quedan menos de 5 unidades del producto Laptop").

3. **Gestión en Inventory**:
   - La clase `Inventory` inicializa los observadores (`AlertAgent` y `LogAgent`) y los registra automáticamente en cada nuevo producto creado mediante `addProduct`.
   - Cuando se actualiza el stock con `modifyStock`, se delega a `Product.modifyStock`, lo que desencadena la notificación a los observadores.

### Beneficios del Patrón Observer
- **Desacoplamiento**: Los productos no necesitan conocer los detalles de los observadores, solo que implementan `StockObserver`.
- **Extensibilidad**: Se pueden añadir nuevos observadores (e.g., uno que envíe correos) sin modificar `Product` o `Inventory`, cumpliendo con el **Open-Closed Principle (OCP)**.
- **Reusabilidad**: Los observadores son independientes y pueden usarse en otros contextos si es necesario.

## Aplicación de Principios SOLID
- **Single Responsibility Principle (SRP)**: Cada clase tiene una única responsabilidad:
  - `Product`: Maneja datos del producto y notificaciones.
  - `Inventory`: Gestiona la colección de productos.
  - `AlertAgent` y `LogAgent`: Manejan lógicas específicas de notificación.
- **Open-Closed Principle (OCP)**: El sistema permite añadir nuevos observadores sin modificar el código existente.
- **Liskov Substitution Principle (LSP)**: Cualquier implementación de `StockObserver` puede sustituirse sin afectar el comportamiento.
- **Interface Segregation Principle (ISP)**: La interfaz `StockObserver` es mínima y específica.
- **Dependency Inversion Principle (DIP)**: `Product` depende de la abstracción `StockObserver`, no de implementaciones concretas.
