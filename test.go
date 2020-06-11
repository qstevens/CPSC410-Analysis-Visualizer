func main() {
    fun(d, g, a)
}

func fun() {
    main()
    fun(d)
}

func fun(n) {
    main()
}

func fun2() {
    main()
    fun()
    fun2()
}

func fun(n, m, p) {
    fun()
}

func alone(){
}