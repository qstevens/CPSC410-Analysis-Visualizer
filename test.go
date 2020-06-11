func main() {
    fun(d, g, a)
}

func fun() {
    main()
    fun(d)
    fun(n, m, p)
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

func loneNode(){
}