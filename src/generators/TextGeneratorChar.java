package generators;

public abstract class TextGeneratorChar extends TextGenerator{
    protected abstract char generateNewChar();
    @Override
    public void generateNew(int characters) {
        for(int i=0;i<characters;i++){
            generated.append(generateNewChar());
        }
        lengthNew = characters;
    }
}
