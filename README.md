This is a giter8 template for sbt 0.13.x projects in the style that
I (inkytonik) commonly use. The generated project will be set up to use the
[Kiama language processing library](http://kiama.googlecode.com) and will
contain a simple example of Kiama use.

See [`inkytonik/kiama-rats.g8`](https://github.com/inkytonik/kiama-rats.g8) for
a variant that combines Kiama with the Rats! parser generator, and
[`inkytonik/plain.g8`](https://github.com/inkytonik/plain.g8) for a variant
that does not use Kiama or Rats!.

### Properties

* illustration of how to use simple Kiama features to build a simple
language processor which is a REPL for a simple expression language

* examples of using ScalaTest and ScalaCheck ests with Kiama-based programs

* scalac options `-deprecation` and `-unchecked` turned on

* sbt log level is warning

* sbt shell prompt contains project name and version

* no project sub-directory

* sources and resources located directly under `src`

* stub `Main` object with a dummy `main` method

* tests located with sources with suffix `Tests.scala`

* Mercurial `.hgignore` file

### Usage

Install [sbt 0.13.x](http://www.scala-sbt.org).

If using sbt 0.13.13 or above:

    sbt new inkytonik/kiama.g8

Or alternatively, install [giter8 (g8)](http://github.com/n8han/giter8#readme) and in a shell run the following:

    g8 inkytonik/kiama

The `g8` command will prompt you for information needed to setup the
project.

Run the generated project to get a REPL as follows:

    cd $name$
    sbt
    > run
    exp> 0 + 4 * 1
    e = Add(Num(0),Mul(Num(4),Num(1)))
    e tree:
    Add (Num (0), Mul (Num (4), Num (1)))
    e tree pretty printed:
    (0 + (4 * 1))
    value (e) = 4
    e optimised = Num(4)
    value (e optimised) = 4
    exp> ^D
    >

where `$name$` is the value you entered when running the `g8` command.
Use Control-D to get out of the REPL.
