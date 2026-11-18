class MicroBlog {
    public String truncate(String input) {
        int count = input.codePointCount(0, input.length());
         if(count <=5) {
            return input;
         }
        int end = input.offsetByCodePoints(0, 5);
        return input.substring(0,end);
    }
}
