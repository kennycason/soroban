class Soroban < Formula
  desc "Soroban: A Command Line Calculator"
  homepage "https://github.com/kennycason/soroban"
  url "http://search.maven.org/remotecontent?filepath=com/kennycason/soroban/1.1/soroban-1.1.jar"
  sha256 "38b71bd58d677fee3063bf3bfacfa1e3f90a842875d5fd02016f0c9adf8080fc"

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
