# CompanyResearch

Tenez à jour la liste des enterprises que vous avez contacté pour un emplois ou un autre motif

Keep track of companies you contact for a job

# How its works

Import the project in your IDE (eclipse: import project as zip)

## Required libraries
 - Joda Time 2.10.3
 - Commons io 2.6
 - Commons fileupload 1.4
 - Jstl 1.2

Note: all required libs are under `WebContent>WEB-INF>lib`

## Code structure
The code is structured by the following manied:

WebContent>WEB-INF : html interface (using semantic ui)
src>fr>tutosfaciles48> beans forms servlets util (self described)
src>com>mkyong> csv util : wraper for read/write csv file

## Complement note
This code was tested by using Tomcat 9 on Windows 10 64b

(i am going to make other test later in the year)

# Screenshots

![Intreface d'accueil](https://scontent.tutosfaciles48.fr/assets/img/18a24267-7b6a-4b4d-9941-5fe6a49045b6.png)

![Exemple d'utilisation](https://scontent.tutosfaciles48.fr/assets/img/8de72c0b-2bbe-495a-b1ac-c5d6d61b4b5f.png)

Note: pour le moment, l'export en pdf n'est pas implémenté, seul l'export en csv et l'import fonctionnent.

Note: for the moment, the export in pdf is not implemented, only the export in csv and the import work.

Nb 2: L'interface est en français

The UI is in french

N'hésitez pas à proposer des Pull Request ou des améliorations à intégrer

Do not hesitate to propose Pull Request or improvements to integrate
