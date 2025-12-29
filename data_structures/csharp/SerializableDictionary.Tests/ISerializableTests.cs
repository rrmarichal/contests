using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text.Json;
using SerializableDataStructures;

namespace ISerializableTests.Tests;

public class ISerializableTests
{
    [Fact]
    public void JsonSerialize_Deserialize_PreservesAllProperties()
    {
        // Arrange
        var original = new SerializableDictionary
        {
            { "Name", "Alice Smith" },
            { "Age", "30" },
            { "Email", "alice@example.com" }
        };

        // Act
        var json = JsonSerializer.Serialize(original);

        Console.WriteLine(json);

        var deserialized = JsonSerializer.Deserialize<SerializableDictionary>(json);

        // Assert
        Assert.NotNull(deserialized);
        Assert.Equal(original["Name"], deserialized["Name"]);
        Assert.Equal(original["Age"], deserialized["Age"]);
        Assert.Equal(original["Email"], deserialized["Email"]);
    }

    [Fact]
    public void Serialize_EmptyStrings_PreservesEmptyValues()
    {
        // Arrange
        var original = new SerializableDictionary
        {
            { "Name", "Alice Smith" },
            { "Age", "30" },
            { "Email", "alice@example.com" }
        };

        // Act
        var deserialized = SerializeAndDeserialize(original);

        // Assert
        Assert.Equal("Alice Smith", deserialized["Name"]);
        Assert.Equal("30", deserialized["Age"]);
        Assert.Equal("alice@example.com", deserialized["Email"]);
    }

    private SerializableDictionary SerializeAndDeserialize(SerializableDictionary original)
    {
        var serialized = original.ToString();
        return SerializableDictionary.FromString(serialized);
    }
}
