package constants;

import com.example.telegrambotanimalshelter.models.Adopter;

import java.util.ArrayList;
import java.util.List;

import static constants.AdopterConstant.*;

public class AdopterServiceConstant {
    public static final List<Adopter> ADOPTERS_OF_SHELTER_1 = new ArrayList<>(List.of(ADOPTER1, ADOPTER2));
    public static final List<Adopter> ADOPTERS_OF_SHELTER_2 = new ArrayList<>(List.of(ADOPTER3, ADOPTER4));
    public static final List<Adopter> ACTUAL_ADOPTERS = new ArrayList<>(List.of(ADOPTER1, ADOPTER2, ADOPTER3, ADOPTER4));



}
