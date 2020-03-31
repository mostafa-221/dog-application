# dogs-example

Bevat voorbeeld spring boot met JPA. Daarnaast uit te breiden met unit tests en voorbeelden van Mockito.


## Toegevoegd:

- Foutafhandeling in de controller
via de templates error.html en ExpedtedError.html. 

Toelichting: 
De ExampleController de ErrorController laten extenden:

public class ExampleController implements ErrorController { ...

Override de methode om het errorpad te verkrijgen:

@Override
    public String getErrorPath() ...

Onverwachte fouten leiden ertoe dat error.html wordt getoond, verwachte fouten tonen de pagina
ExpectedError.html met een foutmelding. Zie voor een voorbeeld addDogSubmit in de ExampleController.


## Toegevoegd:
Mockito via de pom.xml. 

        <dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-engine</artifactId>
			<version>5.4.0</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
			<version>2.27.0</version>
		</dependency>
		
Testen met mocks van mockito, voorbeeld in ExampleControllerTest.java.
Zo zal public void addDogSubmit_ageZero() throws Exception {... testen of bij een hond met een leeftijd van 0
de error pagina wordt aangeroepen en de methode add() van de service niet wordt aangeroepen.
Uiteraard mag de leeftijd best 0 zijn maar dit is een voorbeeld van valideren.

### Opdracht 1 Endpoint /delete_dog compleet maken

a. Delete endpoint implementeren
De delete methode werkt nog niet (endpoint /delete_dog). Bouw deze zodat de hond wel wordt verwijderd.
b. Delete methode van error afhandeling voorzien
De delete methode graag voorzien van error afhandeling. Als de hondenaam niet wordt ingevuld error pagina tonen met juiste mededeling. Als de hond niet kan worden gevonden de error pagina tonen met juiste mededeling
c. Tests met mockito ontwikkelen
Bouw tests om de verschillende afhandelingspaden van /delete_dog in de controller zo volledig mogelijk te teesten
Voer de tests in IntelliJ uit als test suite inclusief de controle op de dekking.
Check de getoonde paden in IntelliJ van de test: welke delen van de code worden al dan niet getest? Hoe kun je dat zien?

### Opdracht 2 Bouw unit tests met Mockito voor de overige klassen
a. Bouw de unit tests voor een van de overige klassen
b. Check de dekking van deze unit tests.
Hoe kan ik zien welke paden wel en welke niet zijn gedekt?
Hoe kan ik zien welk percentage van de code is getest in intelliJ?
Hoe kan ik zien welke van de tests nog falen in intelliJ?


