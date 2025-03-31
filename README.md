# Teoria: Activities amb Android

## Què és una Activity?

Enllaç a la [documentació oficial de android](https://developer.android.com/guide/components/activities/intro-activities?hl=es-419)
A Android, una **Activity** és una de les parts fonamentals d'una aplicació. Representa una pantalla única de la interfície d'usuari (UI). Cada Activity s'associa amb una interfície visual en què els usuaris poden interactuar, com un formulari, un menú o una vista de llista.

### Cicle de vida d'una Activity

Cada Activity a Android segueix un cicle de vida que defineix com s'inicia, pausa, es reprèn i es finalitza. Aquest cicle de vida és gestionat pel sistema opèratiu (a partir d'una pila anomenada **Back Stack** que gestiona les Activities), i les transicions entre aquests estats es realitzen mitjançant mètodes específics de la classe `Activity`.

Tots els **dispositius Android inclouen un botó Enrere** per a aquesta mena de navegació, per la qual cosa no és necessari que la teva app el tengui.

Quan una activitat en curs inicia una altra, aquesta **nova activitat obté focus i és empesa a la part superior de la pila**. L'**activitat anterior** segueix a la **pila** però detinguda.

Si l'usuari torna (back), l'activitat que tenia el focus s'elimina, i l'activitat prèvia que estrobava sota ella en la pila es restaura i torna a agafar el focus (amb el context que teniem abans, p.ex. si hi havia un valor dins un editText encara hi serà).

Si es continua presionant cap endarrera s'aniran eliminant successivament tasques fins a la pantalla d'inici. Quan totes les activitats s'han eliminat de la pila, la tasca deixa d'existir i s'elimina de la memòria.

1. **onCreate()**: Es crida quan l'Activity és creada. Aquí és on has d'inicialitzar els components de la interfície d'usuari i altres recursos necessaris.
2. **onStart()**: L'Activity es fa visible per a l'usuari.
3. **onResume()**: L'Activity comença a interactuar amb l'usuari.
4. **onPause()**: L'Activity està a punt de ser interrompuda o posada en segon pla (exemple: una altra Activity està començant).
5. **onStop()**: L'Activity ja no és visible.
6. **onRestart()**: Es crida quan l'Activity torna a la pantalla.
7. **onDestroy()**: Es crida quan l'Activity es destrueix, ja sigui perquè s'ha finalitzat o el sistema necessita alliberar recursos.

Quan cream un Activity sempre se'ns crearà per defecte el mètode **onCreate()**, ja que per a que tengui alguna cosa que mostrar haurem de cridar a la funció `setContentView()` passant-li el layout a mostrar.

### Passar dades entre Activities

Les Activities es comuniquen entre si a través d'**Intents**. Un `Intent` és un objecte que descriu una operació a realitzar i pot incloure dades.

- Exemple d'`Intent` explícit (especificant una Activity concreta):

```java
Intent intent = new Intent(this, SecondActivity.class);
startActivity(intent);
```

- Exemple d'`Intent` implícit (sense especificar una Activity concreta):

```java
Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"));
startActivity(intent);
```

- Exemple d'`Intent` afegint informació extra utilitzant el mètode `putExtra()`:

```java
Intent intent = new Intent (this, SecondActivity.class);
intent.putExtra("key", "value");
startActivity(intent);
```

A l'Activity receptora, obtindrem les dades amb `getIntent().getStringExtra()`:

```java
String valor = getIntent().getStringExtra("key");
```

### Esperar un resultat d'una Activity

A vegades, no només volem llançar una nova Activity, sinó que també necessitem obtenir un resultat d'aquesta Activity. Per aconseguir-ho, podem utilitzar el mètode `startActivityForResult()`. Aquest mètode permet a l'Activity que es llança retornar dades a l'Activity que la va cridar.

- Exemple d'obrir una Activity esperant una resposta:

```java
SECOND_ACTIVITY_CODE = 1

Intent intent = new Intent(this, SecondActivity.class);
startActivityForResult(intent, SECOND_ACTIVITY_CODE); // 1 és el codi de petició, podem posar el que volem
```

Per enviar el resultat des de SecondActivity:

```java
Intent resultIntent = new Intent();
resultIntent.putExtra("result", "Dades retornades");
setResult(RESULT_OK, resultIntent);
finish(); // Tancam l'activitat
```

Per recollir el resultat haurem de sobreescriure la funció `onActivityResult(int requestCode, int resultCode, Intent data)`.

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // Resultat de la SecondActivity
    if (requestCode == SECOND_ACTIVITY_CODE) {
        // El resultat és OK
        if (resultCode == RESULT_OK) {
            // Tractam el resultat que mos haurà tornat de la SecondActivity amb el putExtra("key", "value")
            Toast.makeText(getApplicationContext(), data.getExtras().getString("torna"), Toast.LENGTH_SHORT).show();
        }
    }
    super.onActivityResult(requestCode, resultCode, data);
}
```

El problema que tenim ara és que el que hem vist fins ara ja està obsolet amb la darrera versió d'Android. L'`startActivityForResult` s'ha substituÏt per `ActivityResultLauncher` i `ActivityResultContracts`. Aquest nou sistema és més flexible i recomanat per a versions posteriors a **Android 11** (API 30) per millorar la seguretat i la facilitat d'ús.

Per fer-ho amb la nova versió primer de tot haurem de definir l'`ActivityResultLauncher` i llançar l'Activity:

```java
ActivityResultLauncher<Intent> activityResultLauncher =
    registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Toast.makeText(MainActivity.this,
                                result.getData().getStringExtra(("result")),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
```

Llançam la segona Activity:

```java
Intent intent = new Intent(MainActivity.this, SecondActivity.class);
activityResultLauncher.launch(intent);
```

Retorn d'un resultat des de l'Activity secundària:

```java
Intent resultIntent = new Intent();
resultIntent.putExtra("result", "Dades retornades");
setResult(RESULT_OK, resultIntent);
finish(); // Tancar l'Activity
```

### Creació d'una Activity a Android Studio

1. **Crear una nova Activity**: Ves al menú `"File" > "New" > "Activity"` i selecciona el tipus d'Activity que desitges crear, per exemple, **Empty Activity**.

2. **Modificar l'arxiu del manifest**: Cada Activity s'ha de declarar a l'arxiu `AndroidManifest.xml`.

```xml
<activity android:name=".SecondActivity"></activity>
```

3. **Configuració de la interfície d'usuari (UI)**: Pots dissenyar la UI de l'Activity utilitzant arxius XML al directori `res/layout/`.
