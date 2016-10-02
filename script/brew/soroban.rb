class Soroban < Formula
  desc "Soroban: A Command Line Calculator"
  homepage "https://github.com/kennycason/soroban"
  url "http://search.maven.org/remotecontent?filepath=com/kennycason/soroban/1.4/soroban-1.4.jar"
  sha256 "dd130df186f41d7326e2ec649d8ee3bd58a0348bfb484764693465e9c1c9f80e"

  def install
    libexec.install "soroban-1.4.jar"
    bin.write_jar_script libexec/"soroban-1.4.jar", "soroban"
    puts "Finished installing Soroban 1.4"
  end

  test do
    pipe_output("#{bin}/soroban -v", "Test Soroban version command")
  end
end
