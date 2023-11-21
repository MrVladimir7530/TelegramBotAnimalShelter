package constants;

import com.example.telegrambotanimalshelter.models.Adopter;

import static constants.AnimalConstant.*;
import static constants.SubscriberConstant.*;

public class AdopterConstant {
    public static final Adopter ADOPTER1 = new Adopter(1L,30, SUBSCRIBER1, ANIMAL_CAT1);
    public static final Adopter ADOPTER1_45TP = new Adopter(1L,45, SUBSCRIBER1, ANIMAL_CAT1);
    public static final Adopter ADOPTER2 = new Adopter(2L,30, SUBSCRIBER2, ANIMAL_CAT2);
    public static final Adopter ADOPTER2_45TP = new Adopter(2L,45, SUBSCRIBER2, ANIMAL_CAT2);
    public static final Adopter ADOPTER3 = new Adopter(3L, 30,SUBSCRIBER3, ANIMAL_DOG1);
    public static final Adopter ADOPTER4 = new Adopter(4L, 30,SUBSCRIBER4, ANIMAL_DOG2);


}
