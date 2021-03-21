public class RocketLabDriver {
    public static void main(String[] args) {
        final String desc =
                "As a rocket engineer, you need to build a rocket using the various parts\n"
                        + "available in the lab. You may have to combine individual parts to"
                        + " assemble\n"
                        + "a more complex part of the rocket. In some cases, you might be able to\n"
                        + "purchase a fully assembled and function part of the rocket using the\n"
                        + "available time. If you are stuck with no options, look for hints.\n";
        final String rocket =
                ""
                        + "                  .─.\n"
                        + "                 ╱...╲\n"
                        + "                :     :\n"
                        + "                │     │\n"
                        + "                │_____│\n"
                        + "                :_____:\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │=====│\n"
                        + "                │.....│\n"
                        + "                │=====│\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "               *│┌...┐│*\n"
                        + "              **││...││**\n"
                        + "             ***││...││***\n"
                        + "            ****││...││****\n"
                        + "           *****││...││*****\n"
                        + "          ******││...││******\n"
                        + "         *******│└...┘│*******\n"
                        + "        ********└──*──┘********\n"
                        + "       *********   *   *********\n"
                        + "      ********     *     ********\n"
                        + "     ********      *      ********\n"
                        + "\n\n";
        final String intro =
                "The last hope for humanity lies in your hands. You have to build a rocket\n"
                    + "and take off to escape the pending destruction of earth and the eventual\n"
                    + "end to life. Remember to \"blast off\" after building the rocket.\n";

        RocketLab lab = new RocketLab(desc, rocket + intro, 180);
        new EscapeApp(lab).runGame();
    }
}
