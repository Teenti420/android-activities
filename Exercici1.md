# Exercici 1: "Aplicació de navegació entre Activities amb retorn de dades"

## Objectiu:

Crear una aplicació Android amb tres Activities, en què les dades passen d'una a una altra. La funcionalitat ha de permetre l'intercanvi de dades entre les tres pantalles.

## Detalls de la pràctica:

1. **MainActivity (Activity 1):**

   - Contindrà un `EditText` on l'usuari introdueix un número.
   - Un `Button` que, en fer clic, obrirà `SecondActivity` passant-li el número introduït.
   - Quan `SecondActivity` tanqui, el número es mostrarà de nou a `MainActivity`.

2. **SecondActivity (Activity 2):**

   - Contindrà un `TextView` amb el número que es va passar des de `MainActivity`.
   - Un `Button` que, en fer clic, obrirà `ThirdActivity`, passant el número que tenim dins el textView.
   - Quan `ThirdActivity` es tanqui, `SecondActivity` rebrà el número de `ThirdActivity` que pot ser que hagi estat modificat. Aquest s'haurà d'actualitzar.
   - Un altre `Button` permetrà tornar a `MainActivity`, on no s'enviarà res, simplement hi ha d'haver l'edit text amb el contingut que hi havia abans.

3. **ThirdActivity (Activity 3):**
   - Contindrà un `EditText` amb la mateixa dada que se'ns havia passat de `SecondActivity`.
   - Un `Button` que, en fer clic, permetrà tornar a `SecondActivity` amb la informació de la dada de l'edit text modificada o no (si no hi ha hagut canvis).
