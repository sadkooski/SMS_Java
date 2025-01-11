# Student Management System (SMS)

## Przegląd

Student Management System (SMS) to aplikacja Java, która pozwala na zarządzanie danymi studentów przy użyciu graficznego interfejsu użytkownika (GUI). Użytkownicy mogą dodawać, usuwać, aktualizować i wyświetlać dane studentów, a także obliczać średnią ocen wszystkich studentów. Aplikacja jest połączona z bazą danych, która przechowuje dane studenckie w tabeli `students` w systemie SQLite.

## Funkcjonalności

Aplikacja oferuje następujące funkcjonalności:

1. **Dodawanie studenta**: Umożliwia dodanie nowego studenta do bazy danych przy użyciu formularza.
2. **Usuwanie studenta**: Umożliwia usunięcie studenta z bazy danych na podstawie unikalnego ID.
3. **Wyświetlanie wszystkich studentów**: Zobrazuje listę wszystkich studentów zapisanych w bazie danych.
4. **Obliczanie średniej ocen**: Oblicza i wyświetla średnią ocen wszystkich studentów.
5. **Aktualizowanie danych studenta**: Ta funkcjonalność jest planowana do zaimplementowania.

## Instrukcje dotyczące kompilacji i uruchamiania aplikacji

### Wymagania:

- JDK 8 lub nowsze
- SQLite (baza danych)
- IDE obsługujące Jave, np. IntelliJ IDEA lub Eclipse

### Kompilacja i uruchamianie:

1. **Klonowanie repozytorium**: Pobierz projekt z repozytorium (lub skopiuj pliki do swojego folderu roboczego).

2. **Kompilacja**:
   - W terminalu (lub IDE) przejdź do katalogu, w którym znajduje się projekt.
   - Skorzystaj z następującego polecenia kompilacji:
     ```bash
     javac -d bin src/*.java
     ```
   - Zostanie utworzony folder `bin`, w którym pojawią się skompilowane pliki `.class`.

3. **Uruchomienie aplikacji**:
   - Uruchom aplikację, używając następującego polecenia:
     ```bash
     java -cp bin main.Main
     ```
   - Aplikacja uruchomi GUI systemu zarządzania studentami.

## Konfiguracja bazy danych

Aplikacja używa SQLite jako bazy danych. Baza danych `students.db` powinna zostać utworzona automatycznie, ale w przypadku potrzeby ręcznego utworzenia bazy danych, należy wykonać poniższe kroki:

1. **Utworzenie bazy danych**:
   Jeśli plik `students.db` jeszcze nie istnieje, SQLite utworzy go automatycznie przy pierwszym uruchomieniu aplikacji.

2. **Tworzenie tabeli `students`**:
   Jeśli baza danych istnieje, ale tabela `students` nie została jeszcze utworzona, aplikacja automatycznie wykona polecenie SQL do jej stworzenia.

   Jeśli chcesz ręcznie utworzyć tabelę, użyj poniższego zapytania SQL:
   ```sql
   CREATE TABLE IF NOT EXISTS students (
       studentID TEXT PRIMARY KEY,
       name TEXT NOT NULL,
       age INTEGER NOT NULL,
       grade REAL NOT NULL
   );