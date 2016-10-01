class Soroban < Formula
  desc "Soroban: A Command Line Calculator"
  homepage "https://github.com/kennycason/soroban"
  url "http://search.maven.org/remotecontent?filepath=com/kennycason/soroban/1.1/soroban-1.1.jar"
  sha256 "384d5861f74894e0f4fd4cd70ae09572d311377e61ba06ded6aff3edf22789af"

  def install
    libexec.install "soroban-1.1.jar"
    bin.write_jar_script libexec/"soroban-1.1.jar", "soroban"
    puts "Finished installing Soroban 1.1"
  end

  def server_script(server_jar); <<-EOS.undent
    #!/bin/bash
    exec java -cp #{server_jar} com.kennycason.soroban.cli.SorobanCli "$@"
  EOS
  end

  test do
    pipe_output("#{bin}/soroban -v", "Test Soroban version command")
  end
end
