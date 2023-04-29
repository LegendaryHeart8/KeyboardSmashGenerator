package generators;

public class RandomGenerator extends TextGeneratorChar{
    @Override
    protected char generateNewChar() {
        return indToLetter(random.nextInt(26));
    }
}
