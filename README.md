# API_StockProd_Security
Práctica Security - Roles y permisos


Crear una API que gestione el ciclo de vida de los productos de una tienda. 

Los productos tienen : nombre, precio , stock

La API permite a todos los usuarios registrados: 
- listar todos los productos                                       GET api/productos
- listar un prod por ID                                            GET api/productos/{id_prod}
- listar los prod que tengan un determinado string en el nombre    GET api/productos/{nombre}

Además, los admin pueden:   
- modificar un prod existente                                     POST api/productos/guardar
- crear nuevos prod                                               PUT api/productos/guardar
- eliminar un prod por ID                                         DELETE api/productos/{id_prod}    

