SQLITE


Du bestämmer själv vad databasen har för syfte, t ex Att-göra-lista, Anteckningar, Recept.
• Vi använder SQLite och skriver kommandon för att skapa tabeller etc.
• Vi gör ett program i Java med JDBC mot databasen.
• Inlämningsuppgiften ska göras individuellt.
• Du ska filma en kort demo som visar att Java-programmet fungerar som det är tänkt som 
lämnas in tillsammans med labben (se nedan). Filmen behöver visa att alla kraven är med för 
labben för G och även för VG om man har gjort det. Använd t ex programmet OBS för att 
göra filmen



Krav
• Du ska använda SQLite som databas.
• Du skriver kommandon i databasen i kommandotolken, Powershell eller liknande (alternativt 
i Datagrip) för att skapa databasen och kolla att den fungerar genom att skriva SQL-frågor 
direkt mot den.
• Du gör ett program i Java (konsollapplikation) som använder JDBC mot SQLite-databasen. 
• Programmet (konsollapplikationen) har CRUD-funktionalitet (dvs kan lägga till, visa, 
uppdatera och radera) där användaren kan mata in (genom scanner) och visa relevant data
genom ett menysystem (se exempelkod lektionSQLiteCRUD). Du skriver relevanta metoder i 
Java för detta.
• Du ska ha ett-till-många förhållande i databasen (två tabeller). Du ska ha CRUD-funktionalitet 
för båda tabellerna. Men det räcker med fullständig CRUD dvs lägga till, visa, uppdatera och 
radera för den ena av tabellerna i programmet. För den andra tabellen kan du ha med delar 
av CRUD i programmet.
• Du ska ha ett val i programmet som visar en JOIN från båda tabellerna.
