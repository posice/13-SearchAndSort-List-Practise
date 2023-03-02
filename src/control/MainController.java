package control;

import model.List;
import model.Person;

public class MainController {

    private List<Person> allPersons;
    private String[] names = {"Alsbach", "Bachmann", "Cyrus", "Davidoff", "Eregon", "Füller","Giesehau","Halidsch","Irimoff","Zylla","Yilderim","Lupp","Schein","Xenomo","Iwan","Ali","Kötter","Kleinhans","Diablo","Overwatch","Starcraft","Warcraft","Minecraft","Krunker","Command","And","Conquer","Path","Exile","Valheim"};

    public MainController(int amount){
        allPersons = new List<>();
        for(int i = 0; i < amount; i++){
            allPersons.append(createPerson());
        }
    }

    private Person createPerson(){
        Person p = new Person(getRandomName());
        return p;
    }

    public String getRandomName(){
        return names[(int)(Math.random()*names.length)];
    }

    public String showList(){
        String output = "Ausgabe: ";
        allPersons.toFirst();
        while(allPersons.hasAccess()){
            output = output + allPersons.getContent().getName()+" ("+allPersons.getContent().getBirthdate()+"), ";
            allPersons.next();
        }
        return output;
    }

    /**
     * Schreibe einen Algorithmus zum Suchen einer Person innerhalb einer Liste. Falls die Person gefunden wurde, gib ihren Namen samt Geburtsdatum aus.
     * @param name
     * @return
     */
    public String searchList(String name){
        String output = "Nicht gefunden.";
        //TODO 01: Schreibe einen Suchalgorithmus
        allPersons.toFirst();
        while (allPersons.hasAccess()){
            if (allPersons.getContent().getName().equals(name)){
                output = allPersons.getContent().getName();
                return output;
            }
            allPersons.next();
        }
        return output;
    }

    /**
     * Stortiere die Liste nach Namen (nicht nach Geburtsdatum!). Entscheide dich hierzu für einen Sortieralgorithmus.
     * Gib an, ob deine Umsetzung stabil ist und ob sie in-place ist.
     */
    public void sortList(){
        //TODO 02: Schreibe einen Sortieralgorithmus
        List<Person> helpList = new List<>();
        while (!allPersons.isEmpty()){
            allPersons.toFirst();
            Person first  = allPersons.getContent();
            while (allPersons.hasAccess()){
                if (allPersons.getContent().getName().compareTo(first.getName()) < 0 ){
                    first = allPersons.getContent();
                }
                allPersons.next();
            }
            helpList.append(first);
            allPersons.toFirst();
            while (allPersons.getContent().getName().compareTo(first.getName())!= 0){
                allPersons.next();
            }
            allPersons.remove();
        }
        allPersons.concat(helpList);
        // Stabil und in-Place, da ich bei gleichem Namen immer den erst genannten Namen nach vorne packe und immer nur eine Liste erstelle
    }

    private List<Person> quicksortList(List<Person> list){
        list.toFirst();
        list.next();
        if (list.isEmpty() || !list.hasAccess()){
            return list;
        }
        list.toFirst();
        Person pivot = list.getContent();
        list.remove();
        List<Person> left = new List<>();
        List<Person> right = new List<>();

        while (list.hasAccess()){
            if (list.getContent().getName().compareTo(pivot.getName())<= 0) left.append(list.getContent());
            else                                                            right.append(list.getContent());
            list.remove();
        }

        left = quicksortList(left);
        right = quicksortList(right);

        list.concat(left);
        list.append(pivot);
        list.concat(right);

        return list;
    }
}
