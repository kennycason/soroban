class Soroban < Formula
  desc "Soroban: A Command Line Calculator"
  homepage "https://github.com/kennycason/soroban"
  url "http://search.maven.org/remotecontent?filepath=com/kennycason/soroban/1.4/soroban-1.4.jar"
  sha256 "e1a6c9c7585c1c5e0f146fdba5f09f80ef6623388afeb3b6fbe8c0a6a9179e24"

  def install
    libexec.install "soroban-1.4.jar"
    bin.write_jar_script libexec/"soroban-1.4.jar", "soroban"
    puts "Finished installing Soroban 1.4"
  end

  test do
    pipe_output("#{bin}/soroban -v", "Test Soroban version command")
  end
end
