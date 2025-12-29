using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Runtime.Serialization;
using System.Text;
using System.Text.Json;
using System.Text.Json.Serialization;

namespace SerializableDataStructures
{
    [JsonConverter(typeof(SerializableDictionaryJsonConverter))]
    public class SerializableDictionary : IEnumerable<KeyValuePair<string, string>>
    {
        public readonly Dictionary<string, string> dict = [];

        public IEnumerator<KeyValuePair<string, string>> GetEnumerator()
        {
            return dict.GetEnumerator();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        public void Add(string key, string value)
        {
            dict.Add(key, value);
        }

        public override string ToString()
        {
            var builder = new StringBuilder();
            foreach (var key in dict.Keys)
            {
                builder.Append($"{key.ToString().Length}:{key}:{dict[key].ToString().Length}:{dict[key]}:");
            }

            return builder.ToString();
        }

        public static SerializableDictionary FromString(string info)
        {
            var result = new SerializableDictionary();
            var index = 0;
            while (index < info.Length)
            {
                var keyStr = Advance(info, index);
                var keyLength = int.Parse(keyStr);
                var key = Consume(info, index + keyStr.Length + 1, keyLength);
                index += keyStr.Length + keyLength + 2;

                var valueStr = Advance(info, index);
                var valueLength = int.Parse(valueStr);
                var value = Consume(info, index + valueStr.Length + 1, valueLength);
                index += + valueStr.Length + valueLength + 2;

                result.Add(key, value);
            }

            return result;
        }

        private static string Advance(string info, int index)
        {
            var result = new StringBuilder();
            while (index < info.Length && info[index] != ':')
            {
                result.Append(info[index++]);
            }

            return result.ToString();
        }

        private static string Consume(string info, int index, int count)
        {
            return info.Substring(index, count);
        }

        public string this[string key]
        {
            get => dict[key];
            set => dict[key] = value;
        }
    }

    public class SerializableDictionaryJsonConverter : JsonConverter<SerializableDictionary>
    {
        public override SerializableDictionary Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
        {
            var dictionary = JsonSerializer.Deserialize<Dictionary<string, string>>(ref reader, options);
            var result = new SerializableDictionary();
            foreach (var kvp in dictionary)
            {
                result.Add(kvp.Key, kvp.Value);
            }
            return result;
        }

        public override void Write(Utf8JsonWriter writer, SerializableDictionary value, JsonSerializerOptions options)
        {
            JsonSerializer.Serialize(writer, value.dict, options);
        }
    }
}
