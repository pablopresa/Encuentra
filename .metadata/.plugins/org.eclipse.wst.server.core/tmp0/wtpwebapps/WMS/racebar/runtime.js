// @observablehq/runtime v4.6.3 Copyright 2019 Observable, Inc.
function e(e, t, n) {
    n = n || {};
    var r = e.ownerDocument,
        i = r.defaultView.CustomEvent;
    "function" == typeof i ? i = new i(t, {
        detail: n
    }) : ((i = r.createEvent("Event")).initEvent(t, !1, !1), i.detail = n), e.dispatchEvent(i)
}

function t(e) {
    return Array.isArray(e) || e instanceof Int8Array || e instanceof Int16Array || e instanceof Int32Array || e instanceof Uint8Array || e instanceof Uint8ClampedArray || e instanceof Uint16Array || e instanceof Uint32Array || e instanceof Float32Array || e instanceof Float64Array
}

function n(e) {
    return e === (0 | e) + ""
}

function r(e) {
    const t = document.createElement("span");
    return t.className = "observablehq--cellname", t.textContent = `${e} = `, t
}
const i = Symbol.prototype.toString;

function o(e) {
    return i.call(e)
}
const {
    getOwnPropertySymbols: s,
    prototype: {
        hasOwnProperty: a
    }
} = Object, {
    toStringTag: l
} = Symbol, u = {}, c = s;

function d(e, t) {
    return a.call(e, t)
}

function h(e) {
    return e[l] || e.constructor && e.constructor.name || "Object"
}

function p(e, t) {
    try {
        const n = e[t];
        return n && n.constructor, n
    } catch (e) {
        return u
    }
}
const f = [{
    symbol: "@@__IMMUTABLE_INDEXED__@@",
    name: "Indexed",
    modifier: !0
}, {
    symbol: "@@__IMMUTABLE_KEYED__@@",
    name: "Keyed",
    modifier: !0
}, {
    symbol: "@@__IMMUTABLE_LIST__@@",
    name: "List",
    arrayish: !0
}, {
    symbol: "@@__IMMUTABLE_MAP__@@",
    name: "Map"
}, {
    symbol: "@@__IMMUTABLE_ORDERED__@@",
    name: "Ordered",
    modifier: !0,
    prefix: !0
}, {
    symbol: "@@__IMMUTABLE_RECORD__@@",
    name: "Record"
}, {
    symbol: "@@__IMMUTABLE_SET__@@",
    name: "Set",
    arrayish: !0,
    setish: !0
}, {
    symbol: "@@__IMMUTABLE_STACK__@@",
    name: "Stack",
    arrayish: !0
}];

function m(e) {
    /*
	try {
        let t = f.filter(({
            symbol: t
        }) => !0 === e[t]);
        if (!t.length) return;
        const n = t.find(e => !e.modifier),
            r = "Map" === n.name && t.find(e => e.modifier && e.prefix),
            i = t.some(e => e.arrayish),
            o = t.some(e => e.setish);
        return {
            name: `${r?r.name:""}${n.name}`,
            symbols: t,
            arrayish: i && !o,
            setish: o
        }
    } catch (e) {
        return null
    }
    */
	return '';
     
     
}
const {
    getPrototypeOf: g,
    getOwnPropertyDescriptors: b
} = Object, v = g({});

function _(n, i, o, s) {
    let a, l, u, c, d = t(n);
    n instanceof Map ? (a = `Map(${n.size})`, l = w) : n instanceof Set ? (a = `Set(${n.size})`, l = y) : d ? (a = `${n.constructor.name}(${n.length})`, l = k) : (c = m(n)) ? (a = `Immutable.${c.name}${"Record"===c.name?"":`(${n.size})`}`, d = c.arrayish, l = c.arrayish ? E : c.setish ? x : $) : s ? (a = h(n), l = C) : (a = h(n), l = S);
    const p = document.createElement("span");
    p.className = "observablehq--expanded", o && p.appendChild(r(o));
    const f = p.appendChild(document.createElement("a"));
    f.innerHTML = "<svg width=8 height=8 class='observablehq--caret'>\n    <path d='M4 7L0 1h8z' fill='currentColor' />\n  </svg>", f.appendChild(document.createTextNode(`${a}${d?" [":" {"}`)), f.addEventListener("mouseup", function(e) {
        e.stopPropagation(), ae(p, M(n, null, o, s))
    }), l = l(n);
    for (let e = 0; !(u = l.next()).done && e < 20; ++e) p.appendChild(u.value);
    if (!u.done) {
        const t = p.appendChild(document.createElement("a"));
        t.className = "observablehq--field", t.style.display = "block", t.appendChild(document.createTextNode("  … more")), t.addEventListener("mouseup", function(t) {
            t.stopPropagation(), p.insertBefore(u.value, p.lastChild.previousSibling);
            for (let e = 0; !(u = l.next()).done && e < 19; ++e) p.insertBefore(u.value, p.lastChild.previousSibling);
            u.done && p.removeChild(p.lastChild.previousSibling), e(p, "load")
        })
    }
    return p.appendChild(document.createTextNode(d ? "]" : "}")), p
}

function* w(e) {
    for (const [t, n] of e) yield q(t, n);
    yield* S(e)
}

function* y(e) {
    for (const t of e) yield P(t);
    yield* S(e)
}

function* x(e) {
    for (const t of e) yield P(t)
}

function* k(e) {
    for (let t = 0, n = e.length; t < n; ++t) t in e && (yield L(t, p(e, t), "observablehq--index"));
    for (const t in e) !n(t) && d(e, t) && (yield L(t, p(e, t), "observablehq--key"));
    for (const t of c(e)) yield L(o(t), p(e, t), "observablehq--symbol")
}

function* E(e) {
    let t = 0;
    for (const n = e.size; t < n; ++t) yield L(t, e.get(t), !0)
}

function* C(e) {
    for (const t in b(e)) yield L(t, p(e, t), "observablehq--key");
    for (const t of c(e)) yield L(o(t), p(e, t), "observablehq--symbol");
    const t = g(e);
    t && t !== v && (yield N(t))
}

function* S(e) {
    for (const t in e) d(e, t) && (yield L(t, p(e, t), "observablehq--key"));
    for (const t of c(e)) yield L(o(t), p(e, t), "observablehq--symbol");
    const t = g(e);
    t && t !== v && (yield N(t))
}

function* $(e) {
    for (const [t, n] of e) yield L(t, n, "observablehq--key")
}

function N(e) {
    const t = document.createElement("div"),
        n = t.appendChild(document.createElement("span"));
    return t.className = "observablehq--field", n.className = "observablehq--prototype-key", n.textContent = "  <prototype>", t.appendChild(document.createTextNode(": ")), t.appendChild(se(e, void 0, void 0, void 0, !0)), t
}

function L(e, t, n) {
    const r = document.createElement("div"),
        i = r.appendChild(document.createElement("span"));
    return r.className = "observablehq--field", i.className = n, i.textContent = `  ${e}`, r.appendChild(document.createTextNode(": ")), r.appendChild(se(t)), r
}

function q(e, t) {
    const n = document.createElement("div");
    return n.className = "observablehq--field", n.appendChild(document.createTextNode("  ")), n.appendChild(se(e)), n.appendChild(document.createTextNode(" => ")), n.appendChild(se(t)), n
}

function P(e) {
    const t = document.createElement("div");
    return t.className = "observablehq--field", t.appendChild(document.createTextNode("  ")), t.appendChild(se(e)), t
}

function A(e) {
    const t = window.getSelection();
    return "Range" === t.type && (t.containsNode(e, !0) || t.anchorNode.isSelfOrDescendant(e) || t.focusNode.isSelfOrDescendant(e))
}

function M(e, n, i, o) {
    let s, a, l, u, c = t(e);
    if (e instanceof Map ? (s = `Map(${e.size})`, a = j) : e instanceof Set ? (s = `Set(${e.size})`, a = O) : c ? (s = `${e.constructor.name}(${e.length})`, a = R) : (u = m(e)) ? (s = `Immutable.${u.name}${"Record"===u.name?"":`(${e.size})`}`, c = u.arrayish, a = u.arrayish ? U : u.setish ? T : D) : (s = h(e), a = I), n) {
        const t = document.createElement("span");
        return t.className = "observablehq--shallow", i && t.appendChild(r(i)), t.appendChild(document.createTextNode(s)), t.addEventListener("mouseup", function(n) {
            A(t) || (n.stopPropagation(), ae(t, M(e)))
        }), t
    }
    const d = document.createElement("span");
    d.className = "observablehq--collapsed", i && d.appendChild(r(i));
    const p = d.appendChild(document.createElement("a"));
    p.innerHTML = "<svg width=8 height=8 class='observablehq--caret'>\n    <path d='M7 4L1 8V0z' fill='currentColor' />\n  </svg>", p.appendChild(document.createTextNode(`${s}${c?" [":" {"}`)), d.addEventListener("mouseup", function(t) {
        A(d) || (t.stopPropagation(), ae(d, _(e, 0, i, o)))
    }, !0), a = a(e);
    for (let e = 0; !(l = a.next()).done && e < 20; ++e) e > 0 && d.appendChild(document.createTextNode(", ")), d.appendChild(l.value);
    return l.done || d.appendChild(document.createTextNode(", …")), d.appendChild(document.createTextNode(c ? "]" : "}")), d
}

function* j(e) {
    for (const [t, n] of e) yield B(t, n);
    yield* I(e)
}

function* O(e) {
    for (const t of e) yield se(t, !0);
    yield* I(e)
}

function* T(e) {
    for (const t of e) yield se(t, !0)
}

function* U(e) {
    let t = -1,
        n = 0;
    for (const r = e.size; n < r; ++n) n > t + 1 && (yield z(n - t - 1)), yield se(e.get(n), !0), t = n;
    n > t + 1 && (yield z(n - t - 1))
}

function* R(e) {
    let t = -1,
        r = 0;
    for (const n = e.length; r < n; ++r) r in e && (r > t + 1 && (yield z(r - t - 1)), yield se(p(e, r), !0), t = r);
    r > t + 1 && (yield z(r - t - 1));
    for (const t in e) !n(t) && d(e, t) && (yield F(t, p(e, t), "observablehq--key"));
    for (const t of c(e)) yield F(o(t), p(e, t), "observablehq--symbol")
}

function* I(e) {
    for (const t in e) d(e, t) && (yield F(t, p(e, t), "observablehq--key"));
    for (const t of c(e)) yield F(o(t), p(e, t), "observablehq--symbol")
}

function* D(e) {
    for (const [t, n] of e) yield F(t, n, "observablehq--key")
}

function z(e) {
    const t = document.createElement("span");
    return t.className = "observablehq--empty", t.textContent = 1 === e ? "empty" : `empty × ${e}`, t
}

function F(e, t, n) {
    const r = document.createDocumentFragment(),
        i = r.appendChild(document.createElement("span"));
    return i.className = n, i.textContent = e, r.appendChild(document.createTextNode(": ")), r.appendChild(se(t, !0)), r
}

function B(e, t) {
    const n = document.createDocumentFragment();
    return n.appendChild(se(e, !0)), n.appendChild(document.createTextNode(" => ")), n.appendChild(se(t, !0)), n
}

function H(e, t) {
    var n = e + "",
        r = n.length;
    return r < t ? new Array(t - r + 1).join(0) + n : n
}

function W(e) {
    return e < 0 ? "-" + H(-e, 6) : e > 9999 ? "+" + H(e, 6) : H(e, 4)
}
var V = Error.prototype.toString;
var G = RegExp.prototype.toString;
const K = 20;

function Y(e) {
    return e.replace(/[\\`\x00-\x09\x0b-\x19]|\${/g, J)
}

function J(e) {
    var t = e.charCodeAt(0);
    switch (t) {
        case 8:
            return "\\b";
        case 9:
            return "\\t";
        case 11:
            return "\\v";
        case 12:
            return "\\f";
        case 13:
            return "\\r"
    }
    return t < 16 ? "\\x0" + t.toString(16) : t < 32 ? "\\x" + t.toString(16) : "\\" + e
}

function X(e, t) {
    for (var n = 0; t.exec(e);) ++n;
    return n
}
var Q = Function.prototype.toString,
    Z = {
        prefix: "async ƒ"
    },
    ee = {
        prefix: "async ƒ*"
    },
    te = {
        prefix: "class"
    },
    ne = {
        prefix: "ƒ"
    },
    re = {
        prefix: "ƒ*"
    };

function ie(e, t, n) {
    var i = document.createElement("span");
    i.className = "observablehq--function", n && i.appendChild(r(n));
    var o = i.appendChild(document.createElement("span"));
    return o.className = "observablehq--keyword", o.textContent = e.prefix, i.appendChild(document.createTextNode(t)), i
}
const {
    prototype: {
        toString: oe
    }
} = Object;

function se(e, t, n, i, s) {
    let a = typeof e;
    switch (a) {
        case "boolean":
        case "undefined":
            e += "";
            break;
        case "number":
            e = 0 === e && 1 / e < 0 ? "-0" : e + "";
            break;
        case "bigint":
            e += "n";
            break;
        case "symbol":
            e = o(e);
            break;
        case "function":
            return function(e, t) {
                var n, r, i = Q.call(e);
                switch (e.constructor && e.constructor.name) {
                    case "AsyncFunction":
                        n = Z;
                        break;
                    case "AsyncGeneratorFunction":
                        n = ee;
                        break;
                    case "GeneratorFunction":
                        n = re;
                        break;
                    default:
                        n = /^class\b/.test(i) ? te : ne
                }
                return n === te ? ie(n, "", t) : (r = /^(?:async\s*)?(\w+)\s*=>/.exec(i)) ? ie(n, "(" + r[1] + ")", t) : (r = /^(?:async\s*)?\(\s*(\w+(?:\s*,\s*\w+)*)?\s*\)/.exec(i)) ? ie(n, r[1] ? "(" + r[1].replace(/\s*,\s*/g, ", ") + ")" : "()", t) : (r = /^(?:async\s*)?function(?:\s*\*)?(?:\s*\w+)?\s*\(\s*(\w+(?:\s*,\s*\w+)*)?\s*\)/.exec(i)) ? ie(n, r[1] ? "(" + r[1].replace(/\s*,\s*/g, ", ") + ")" : "()", t) : ie(n, "(…)", t)
            }(e, i);
        case "string":
            return function(e, t, n, i) {
                if (!1 === t) {
                    if (X(e, /["\n]/g) <= X(e, /`|\${/g)) {
                        const t = document.createElement("span");
                        i && t.appendChild(r(i));
                        const n = t.appendChild(document.createElement("span"));
                        return n.className = "observablehq--string", n.textContent = JSON.stringify(e), t
                    }
                    const o = e.split("\n");
                    if (o.length > K && !n) {
                        const n = document.createElement("div");
                        i && n.appendChild(r(i));
                        const s = n.appendChild(document.createElement("span"));
                        s.className = "observablehq--string", s.textContent = "`" + Y(o.slice(0, K).join("\n"));
                        const a = n.appendChild(document.createElement("span")),
                            l = o.length - K;
                        return a.textContent = `Show ${l} truncated line${l>1?"s":""}`, a.className = "observablehq--string-expand", a.addEventListener("mouseup", function(r) {
                            r.stopPropagation(), ae(n, se(e, t, !0, i))
                        }), n
                    }
                    const s = document.createElement("span");
                    i && s.appendChild(r(i));
                    const a = s.appendChild(document.createElement("span"));
                    return a.className = `observablehq--string${n?" observablehq--expanded":""}`, a.textContent = "`" + Y(e) + "`", s
                }
                const o = document.createElement("span");
                i && o.appendChild(r(i));
                const s = o.appendChild(document.createElement("span"));
                return s.className = "observablehq--string", s.textContent = JSON.stringify(e.length > 100 ? `${e.slice(0,50)}…${e.slice(-49)}` : e), o
            }(e, t, n, i);
        default:
            if (null === e) {
                a = null, e = "null";
                break
            }
            if (e instanceof Date) {
                a = "date", l = e, e = isNaN(l) ? "Invalid Date" : function(e) {
                    return 0 === e.getUTCMilliseconds() && 0 === e.getUTCSeconds() && 0 === e.getUTCMinutes() && 0 === e.getUTCHours()
                }(l) ? W(l.getUTCFullYear()) + "-" + H(l.getUTCMonth() + 1, 2) + "-" + H(l.getUTCDate(), 2) : W(l.getFullYear()) + "-" + H(l.getMonth() + 1, 2) + "-" + H(l.getDate(), 2) + "T" + H(l.getHours(), 2) + ":" + H(l.getMinutes(), 2) + (l.getMilliseconds() ? ":" + H(l.getSeconds(), 2) + "." + H(l.getMilliseconds(), 3) : l.getSeconds() ? ":" + H(l.getSeconds(), 2) : "");
                break
            }
            if (e === u) {
                a = "forbidden", e = "[forbidden]";
                break
            }
            switch (oe.call(e)) {
                case "[object RegExp]":
                    a = "regexp", e = function(e) {
                        return G.call(e)
                    }(e);
                    break;
                case "[object Error]":
                case "[object DOMException]":
                    a = "error", e = function(e) {
                        return e.stack || V.call(e)
                    }(e);
                    break;
                default:
                    return (n ? _ : M)(e, t, i, s)
            }
    }
    var l;
    const c = document.createElement("span");
    i && c.appendChild(r(i));
    const d = c.appendChild(document.createElement("span"));
    return d.className = `observablehq--${a}`, d.textContent = e, c
}

function ae(t, n) {
    t.classList.contains("observablehq--inspect") && n.classList.add("observablehq--inspect"), t.parentNode.replaceChild(n, t), e(n, "load")
}
const le = /\s+\(\d+:\d+\)$/m;
class ue {
    constructor(e) {
        if (!e) throw new Error("invalid node");
        this._node = e, e.classList.add("observablehq")
    }
    pending() {
        const {
            _node: e
        } = this;
        e.classList.remove("observablehq--error"), e.classList.add("observablehq--running")
    }
    fulfilled(t, n) {
        const {
            _node: r
        } = this;
        if ((!(t instanceof Element || t instanceof Text) || t.parentNode && t.parentNode !== r) && (t = se(t, !1, r.firstChild && r.firstChild.classList && r.firstChild.classList.contains("observablehq--expanded"), n)).classList.add("observablehq--inspect"), r.classList.remove("observablehq--running", "observablehq--error"), r.firstChild !== t)
            if (r.firstChild) {
                for (; r.lastChild !== r.firstChild;) r.removeChild(r.lastChild);
                r.replaceChild(t, r.firstChild)
            } else r.appendChild(t);
        e(r, "update")
    }
    rejected(t, n) {
        const {
            _node: i
        } = this;
        for (i.classList.remove("observablehq--running"), i.classList.add("observablehq--error"); i.lastChild;) i.removeChild(i.lastChild);
        var o = document.createElement("div");
        o.className = "observablehq--inspect", n && o.appendChild(r(n)), o.appendChild(document.createTextNode((t + "").replace(le, ""))), i.appendChild(o), e(i, "error", {
            error: t
        })
    }
}
async function ce(e) {
    const t = await fetch(await e.url());
    if (!t.ok) throw new Error(`Unable to load file: ${e.name}`);
    return t
}
ue.into = function(e) {
    if ("string" == typeof e && null == (e = document.querySelector(e))) throw new Error("container not found");
    return function() {
        return new ue(e.appendChild(document.createElement("div")))
    }
};
class FileAttachment {
    constructor(e, t) {
        Object.defineProperties(this, {
            _url: {
                value: e
            },
            name: {
                value: t,
                enumerable: !0
            }
        })
    }
    async url() {
        return this._url
    }
    async blob() {
        return (await ce(this)).blob()
    }
    async arrayBuffer() {
        return (await ce(this)).arrayBuffer()
    }
    async text() {
        return (await ce(this)).text()
    }
    async json() {
        return (await ce(this)).json()
    }
    async stream() {
        return (await ce(this)).body
    }
    async image() {
        const e = await this.url();
        return new Promise((t, n) => {
            const r = new Image;
            new URL(e, document.baseURI).origin !== new URL(location).origin && (r.crossOrigin = "anonymous"), r.onload = () => t(r), r.onerror = () => n(new Error(`Unable to load file: ${this.name}`)), r.src = e
        })
    }
}

function de(e) {
    throw new Error(`File not found: ${e}`)
}

function he(e) {
    return function() {
        return e
    }
}
var pe = {
    math: "http://www.w3.org/1998/Math/MathML",
    svg: "http://www.w3.org/2000/svg",
    xhtml: "http://www.w3.org/1999/xhtml",
    xlink: "http://www.w3.org/1999/xlink",
    xml: "http://www.w3.org/XML/1998/namespace",
    xmlns: "http://www.w3.org/2000/xmlns/"
};
var fe = 0;

function me(e) {
    this.id = e, this.href = new URL(`#${e}`, location) + ""
}
me.prototype.toString = function() {
    return "url(" + this.href + ")"
};
var ge = {
    canvas: function(e, t) {
        var n = document.createElement("canvas");
        return n.width = e, n.height = t, n
    },
    context2d: function(e, t, n) {
        null == n && (n = devicePixelRatio);
        var r = document.createElement("canvas");
        r.width = e * n, r.height = t * n, r.style.width = e + "px";
        var i = r.getContext("2d");
        return i.scale(n, n), i
    },
    download: function(e, t = "untitled", n = "Save") {
        const r = document.createElement("a"),
            i = r.appendChild(document.createElement("button"));
        async function o() {
            await new Promise(requestAnimationFrame), URL.revokeObjectURL(r.href), r.removeAttribute("href"), i.textContent = n, i.disabled = !1
        }
        return i.textContent = n, r.download = t, r.onclick = async t => {
            if (i.disabled = !0, r.href) return o();
            i.textContent = "Saving…";
            try {
                const t = await ("function" == typeof e ? e() : e);
                i.textContent = "Download", r.href = URL.createObjectURL(t)
            } catch (e) {
                i.textContent = n
            }
            if (t.eventPhase) return o();
            i.disabled = !1
        }, r
    },
    element: function(e, t) {
        var n, r = e += "",
            i = r.indexOf(":");
        i >= 0 && "xmlns" !== (r = e.slice(0, i)) && (e = e.slice(i + 1));
        var o = pe.hasOwnProperty(r) ? document.createElementNS(pe[r], e) : document.createElement(e);
        if (t)
            for (var s in t) i = (r = s).indexOf(":"), n = t[s], i >= 0 && "xmlns" !== (r = s.slice(0, i)) && (s = s.slice(i + 1)), pe.hasOwnProperty(r) ? o.setAttributeNS(pe[r], s, n) : o.setAttribute(s, n);
        return o
    },
    input: function(e) {
        var t = document.createElement("input");
        return null != e && (t.type = e), t
    },
    range: function(e, t, n) {
        1 === arguments.length && (t = e, e = null);
        var r = document.createElement("input");
        return r.min = e = null == e ? 0 : +e, r.max = t = null == t ? 1 : +t, r.step = null == n ? "any" : n = +n, r.type = "range", r
    },
    select: function(e) {
        var t = document.createElement("select");
        return Array.prototype.forEach.call(e, function(e) {
            var n = document.createElement("option");
            n.value = n.textContent = e, t.appendChild(n)
        }), t
    },
    svg: function(e, t) {
        var n = document.createElementNS("http://www.w3.org/2000/svg", "svg");
        return n.setAttribute("viewBox", [0, 0, e, t]), n.setAttribute("width", e), n.setAttribute("height", t), n
    },
    text: function(e) {
        return document.createTextNode(e)
    },
    uid: function(e) {
        return new me("O-" + (null == e ? "" : e + "-") + ++fe)
    }
};
var be = {
    buffer: function(e) {
        return new Promise(function(t, n) {
            var r = new FileReader;
            r.onload = function() {
                t(r.result)
            }, r.onerror = n, r.readAsArrayBuffer(e)
        })
    },
    text: function(e) {
        return new Promise(function(t, n) {
            var r = new FileReader;
            r.onload = function() {
                t(r.result)
            }, r.onerror = n, r.readAsText(e)
        })
    },
    url: function(e) {
        return new Promise(function(t, n) {
            var r = new FileReader;
            r.onload = function() {
                t(r.result)
            }, r.onerror = n, r.readAsDataURL(e)
        })
    }
};

function ve() {
    return this
}

function _e(e, t) {
    let n = !1;
    return {
        [Symbol.iterator]: ve,
        next: () => n ? {
            done: !0
        } : (n = !0, {
            done: !1,
            value: e
        }),
        return: () => (n = !0, t(e), {
            done: !0
        }),
        throw: () => ({
            done: n = !0
        })
    }
}

function we(e) {
    let t, n, r = !1;
    const i = e(function(e) {
        n ? (n(e), n = null) : r = !0;
        return t = e
    });
    return {
        [Symbol.iterator]: ve,
        throw: () => ({
            done: !0
        }),
        return: () => (null != i && i(), {
            done: !0
        }),
        next: function() {
            return {
                done: !1,
                value: r ? (r = !1, Promise.resolve(t)) : new Promise(e => n = e)
            }
        }
    }
}

function ye(e) {
    switch (e.type) {
        case "range":
        case "number":
            return e.valueAsNumber;
        case "date":
            return e.valueAsDate;
        case "checkbox":
            return e.checked;
        case "file":
            return e.multiple ? e.files : e.files[0];
        default:
            return e.value
    }
}
var xe = {
    disposable: _e,
    filter: function*(e, t) {
        for (var n, r = -1; !(n = e.next()).done;) t(n.value, ++r) && (yield n.value)
    },
    input: function(e) {
        return we(function(t) {
            var n = function(e) {
                    switch (e.type) {
                        case "button":
                        case "submit":
                        case "checkbox":
                            return "click";
                        case "file":
                            return "change";
                        default:
                            return "input"
                    }
                }(e),
                r = ye(e);

            function i() {
                t(ye(e))
            }
            return e.addEventListener(n, i), void 0 !== r && t(r),
                function() {
                    e.removeEventListener(n, i)
                }
        })
    },
    map: function*(e, t) {
        for (var n, r = -1; !(n = e.next()).done;) yield t(n.value, ++r)
    },
    observe: we,
    queue: function(e) {
        let t;
        const n = [],
            r = e(function(e) {
                n.push(e), t && (t(n.shift()), t = null);
                return e
            });
        return {
            [Symbol.iterator]: ve,
            throw: () => ({
                done: !0
            }),
            return: () => (null != r && r(), {
                done: !0
            }),
            next: function() {
                return {
                    done: !1,
                    value: n.length ? Promise.resolve(n.shift()) : new Promise(e => t = e)
                }
            }
        }
    },
    range: function*(e, t, n) {
        e = +e, t = +t, n = (i = arguments.length) < 2 ? (t = e, e = 0, 1) : i < 3 ? 1 : +n;
        for (var r = -1, i = 0 | Math.max(0, Math.ceil((t - e) / n)); ++r < i;) yield e + r * n
    },
    valueAt: function(e, t) {
        if (!(!isFinite(t = +t) || t < 0 || t != t | 0))
            for (var n, r = -1; !(n = e.next()).done;)
                if (++r === t) return n.value
    },
    worker: function(e) {
        const t = URL.createObjectURL(new Blob([e], {
                type: "text/javascript"
            })),
            n = new Worker(t);
        return _e(n, () => {
            n.terminate(), URL.revokeObjectURL(t)
        })
    }
};

function ke(e, t) {
    return function(n) {
        var r, i, o, s, a, l, u, c, d = n[0],
            h = [],
            p = null,
            f = -1;
        for (a = 1, l = arguments.length; a < l; ++a) {
            if ((r = arguments[a]) instanceof Node) h[++f] = r, d += "\x3c!--o:" + f + "--\x3e";
            else if (Array.isArray(r)) {
                for (u = 0, c = r.length; u < c; ++u)(i = r[u]) instanceof Node ? (null === p && (h[++f] = p = document.createDocumentFragment(), d += "\x3c!--o:" + f + "--\x3e"), p.appendChild(i)) : (p = null, d += i);
                p = null
            } else d += r;
            d += n[a]
        }
        if (p = e(d), ++f > 0) {
            for (o = new Array(f), s = document.createTreeWalker(p, NodeFilter.SHOW_COMMENT, null, !1); s.nextNode();) i = s.currentNode, /^o:/.test(i.nodeValue) && (o[+i.nodeValue.slice(2)] = i);
            for (a = 0; a < f; ++a)(i = o[a]) && i.parentNode.replaceChild(h[a], i)
        }
        return 1 === p.childNodes.length ? p.removeChild(p.firstChild) : 11 === p.nodeType ? ((i = t()).appendChild(p), i) : p
    }
}
var Ee = ke(function(e) {
        var t = document.createElement("template");
        return t.innerHTML = e.trim(), document.importNode(t.content, !0)
    }, function() {
        return document.createElement("span")
    }),
    Ce = {
        newline: /^\n+/,
        code: /^( {4}[^\n]+\n*)+/,
        fences: Te,
        hr: /^( *[-*_]){3,} *(?:\n+|$)/,
        heading: /^ *(#{1,6}) *([^\n]+?) *#* *(?:\n+|$)/,
        nptable: Te,
        lheading: /^([^\n]+)\n *(=|-){2,} *(?:\n+|$)/,
        blockquote: /^( *>[^\n]+(\n(?!def)[^\n]+)*\n*)+/,
        list: /^( *)(bull) [\s\S]+?(?:hr|def|\n{2,}(?! )(?!\1bull )\n*|\s*$)/,
        html: /^ *(?:comment *(?:\n|\s*$)|closed *(?:\n{2,}|\s*$)|closing *(?:\n{2,}|\s*$))/,
        def: /^ *\[([^\]]+)\]: *<?([^\s>]+)>?(?: +["(]([^\n]+)[")])? *(?:\n+|$)/,
        table: Te,
        paragraph: /^((?:[^\n]+\n?(?!hr|heading|lheading|blockquote|tag|def))+)\n*/,
        text: /^[^\n]+/
    };

function Se(e) {
    this.tokens = [], this.tokens.links = {}, this.options = e || Re.defaults, this.rules = Ce.normal, this.options.gfm && (this.options.tables ? this.rules = Ce.tables : this.rules = Ce.gfm)
}
Ce.bullet = /(?:[*+-]|\d+\.)/, Ce.item = /^( *)(bull) [^\n]*(?:\n(?!\1bull )[^\n]*)*/, Ce.item = Ae(Ce.item, "gm")(/bull/g, Ce.bullet)(), Ce.list = Ae(Ce.list)(/bull/g, Ce.bullet)("hr", "\\n+(?=\\1?(?:[-*_] *){3,}(?:\\n+|$))")("def", "\\n+(?=" + Ce.def.source + ")")(), Ce.blockquote = Ae(Ce.blockquote)("def", Ce.def)(), Ce._tag = "(?!(?:a|em|strong|small|s|cite|q|dfn|abbr|data|time|code|var|samp|kbd|sub|sup|i|b|u|mark|ruby|rt|rp|bdi|bdo|span|br|wbr|ins|del|img)\\b)\\w+(?!:/|[^\\w\\s@]*@)\\b", Ce.html = Ae(Ce.html)("comment", /<!--[\s\S]*?-->/)("closed", /<(tag)[\s\S]+?<\/\1>/)("closing", /<tag(?:"[^"]*"|'[^']*'|[^'">])*?>/)(/tag/g, Ce._tag)(), Ce.paragraph = Ae(Ce.paragraph)("hr", Ce.hr)("heading", Ce.heading)("lheading", Ce.lheading)("blockquote", Ce.blockquote)("tag", "<" + Ce._tag)("def", Ce.def)(), Ce.normal = Ue({}, Ce), Ce.gfm = Ue({}, Ce.normal, {
    fences: /^ *(`{3,}|~{3,})[ \.]*(\S+)? *\n([\s\S]*?)\s*\1 *(?:\n+|$)/,
    paragraph: /^/,
    heading: /^ *(#{1,6}) +([^\n]+?) *#* *(?:\n+|$)/
}), Ce.gfm.paragraph = Ae(Ce.paragraph)("(?!", "(?!" + Ce.gfm.fences.source.replace("\\1", "\\2") + "|" + Ce.list.source.replace("\\1", "\\3") + "|")(), Ce.tables = Ue({}, Ce.gfm, {
    nptable: /^ *(\S.*\|.*)\n *([-:]+ *\|[-| :]*)\n((?:.*\|.*(?:\n|$))*)\n*/,
    table: /^ *\|(.+)\n *\|( *[-:]+[-| :]*)\n((?: *\|.*(?:\n|$))*)\n*/
}), Se.rules = Ce, Se.lex = function(e, t) {
    return new Se(t).lex(e)
}, Se.prototype.lex = function(e) {
    return e = e.replace(/\r\n|\r/g, "\n").replace(/\t/g, "    ").replace(/\u00a0/g, " ").replace(/\u2424/g, "\n"), this.token(e, !0)
}, Se.prototype.token = function(e, t, n) {
    var r, i, o, s, a, l, u, c, d;
    for (e = e.replace(/^ +$/gm, ""); e;)
        if ((o = this.rules.newline.exec(e)) && (e = e.substring(o[0].length), o[0].length > 1 && this.tokens.push({
                type: "space"
            })), o = this.rules.code.exec(e)) e = e.substring(o[0].length), o = o[0].replace(/^ {4}/gm, ""), this.tokens.push({
            type: "code",
            text: this.options.pedantic ? o : o.replace(/\n+$/, "")
        });
        else if (o = this.rules.fences.exec(e)) e = e.substring(o[0].length), this.tokens.push({
        type: "code",
        lang: o[2],
        text: o[3] || ""
    });
    else if (o = this.rules.heading.exec(e)) e = e.substring(o[0].length), this.tokens.push({
        type: "heading",
        depth: o[1].length,
        text: o[2]
    });
    else if (t && (o = this.rules.nptable.exec(e))) {
        for (e = e.substring(o[0].length), l = {
                type: "table",
                header: o[1].replace(/^ *| *\| *$/g, "").split(/ *\| */),
                align: o[2].replace(/^ *|\| *$/g, "").split(/ *\| */),
                cells: o[3].replace(/\n$/, "").split("\n")
            }, c = 0; c < l.align.length; c++) /^ *-+: *$/.test(l.align[c]) ? l.align[c] = "right" : /^ *:-+: *$/.test(l.align[c]) ? l.align[c] = "center" : /^ *:-+ *$/.test(l.align[c]) ? l.align[c] = "left" : l.align[c] = null;
        for (c = 0; c < l.cells.length; c++) l.cells[c] = l.cells[c].split(/ *\| */);
        this.tokens.push(l)
    } else if (o = this.rules.lheading.exec(e)) e = e.substring(o[0].length), this.tokens.push({
        type: "heading",
        depth: "=" === o[2] ? 1 : 2,
        text: o[1]
    });
    else if (o = this.rules.hr.exec(e)) e = e.substring(o[0].length), this.tokens.push({
        type: "hr"
    });
    else if (o = this.rules.blockquote.exec(e)) e = e.substring(o[0].length), this.tokens.push({
        type: "blockquote_start"
    }), o = o[0].replace(/^ *> ?/gm, ""), this.token(o, t, !0), this.tokens.push({
        type: "blockquote_end"
    });
    else if (o = this.rules.list.exec(e)) {
        for (e = e.substring(o[0].length), s = o[2], this.tokens.push({
                type: "list_start",
                ordered: s.length > 1
            }), r = !1, d = (o = o[0].match(this.rules.item)).length, c = 0; c < d; c++) u = (l = o[c]).length, ~(l = l.replace(/^ *([*+-]|\d+\.) +/, "")).indexOf("\n ") && (u -= l.length, l = this.options.pedantic ? l.replace(/^ {1,4}/gm, "") : l.replace(new RegExp("^ {1," + u + "}", "gm"), "")), this.options.smartLists && c !== d - 1 && (s === (a = Ce.bullet.exec(o[c + 1])[0]) || s.length > 1 && a.length > 1 || (e = o.slice(c + 1).join("\n") + e, c = d - 1)), i = r || /\n\n(?!\s*$)/.test(l), c !== d - 1 && (r = "\n" === l.charAt(l.length - 1), i || (i = r)), this.tokens.push({
            type: i ? "loose_item_start" : "list_item_start"
        }), this.token(l, !1, n), this.tokens.push({
            type: "list_item_end"
        });
        this.tokens.push({
            type: "list_end"
        })
    } else if (o = this.rules.html.exec(e)) e = e.substring(o[0].length), this.tokens.push({
        type: this.options.sanitize ? "paragraph" : "html",
        pre: '',
        text: o[0]
    });
    else if (!n && t && (o = this.rules.def.exec(e))) e = e.substring(o[0].length), this.tokens.links[o[1].toLowerCase()] = {
        href: o[2],
        title: o[3]
    };
    else if (t && (o = this.rules.table.exec(e))) {
        for (e = e.substring(o[0].length), l = {
                type: "table",
                header: o[1].replace(/^ *| *\| *$/g, "").split(/ *\| */),
                align: o[2].replace(/^ *|\| *$/g, "").split(/ *\| */),
                cells: o[3].replace(/(?: *\| *)?\n$/, "").split("\n")
            }, c = 0; c < l.align.length; c++) /^ *-+: *$/.test(l.align[c]) ? l.align[c] = "right" : /^ *:-+: *$/.test(l.align[c]) ? l.align[c] = "center" : /^ *:-+ *$/.test(l.align[c]) ? l.align[c] = "left" : l.align[c] = null;
        for (c = 0; c < l.cells.length; c++) l.cells[c] = l.cells[c].replace(/^ *\| *| *\| *$/g, "").split(/ *\| */);
        this.tokens.push(l)
    } else if (t && (o = this.rules.paragraph.exec(e))) e = e.substring(o[0].length), this.tokens.push({
        type: "paragraph",
        text: "\n" === o[1].charAt(o[1].length - 1) ? o[1].slice(0, -1) : o[1]
    });
    else if (o = this.rules.text.exec(e)) e = e.substring(o[0].length), this.tokens.push({
        type: "text",
        text: o[0]
    });
    else if (e) throw new Error("Infinite loop on byte: " + e.charCodeAt(0));
    return this.tokens
};
var $e = {
    escape: /^\\([\\`*{}\[\]()#+\-.!_>])/,
    autolink: /^<([^ <>]+(@|:\/)[^ <>]+)>/,
    url: Te,
    tag: /^<!--[\s\S]*?-->|^<\/?\w+(?:"[^"]*"|'[^']*'|[^<'">])*?>/,
    link: /^!?\[(inside)\]\(href\)/,
    reflink: /^!?\[(inside)\]\s*\[([^\]]*)\]/,
    nolink: /^!?\[((?:\[[^\]]*\]|[^\[\]])*)\]/,
    strong: /^__([\s\S]+?)__(?!_)|^\*\*([\s\S]+?)\*\*(?!\*)/,
    em: /^\b_((?:[^_]|__)+?)_\b|^\*((?:\*\*|[\s\S])+?)\*(?!\*)/,
    code: /^(`+)([\s\S]*?[^`])\1(?!`)/,
    br: /^ {2,}\n(?!\s*$)/,
    del: Te,
    text: /^[\s\S]+?(?=[\\<!\[_*`]| {2,}\n|$)/
};

function Ne(e, t) {
    if (this.options = t || Re.defaults, this.links = e, this.rules = $e.normal, this.renderer = this.options.renderer || new Le, this.renderer.options = this.options, !this.links) throw new Error("Tokens array requires a `links` property.");
    this.options.gfm ? this.options.breaks ? this.rules = $e.breaks : this.rules = $e.gfm : this.options.pedantic && (this.rules = $e.pedantic)
}

function Le(e) {
    this.options = e || {}
}

function qe(e) {
    this.tokens = [], this.token = null, this.options = e || Re.defaults, this.options.renderer = this.options.renderer || new Le, this.renderer = this.options.renderer, this.renderer.options = this.options
}

function Pe(e, t) {
    return e.replace(t ? /&/g : /&(?!#?\w+;)/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#39;")
}

function Ae(e, t) {
    return e = e.source, t = t || "",
        function n(r, i) {
            return r ? (i = (i = i.source || i).replace(/(^|[^\[])\^/g, "$1"), e = e.replace(r, i), n) : new RegExp(e, t)
        }
}

function Me(e, t) {
    return je[" " + e] || (/^[^:]+:\/*[^\/]*$/.test(e) ? je[" " + e] = e + "/" : je[" " + e] = e.replace(/[^\/]*$/, "")), e = je[" " + e], "//" === t.slice(0, 2) ? e.replace(/:[\s\S]*/, ":") + t : "/" === t.charAt(0) ? e.replace(/(:\/*[^\/]*)[\s\S]*/, "$1") + t : e + t
}
$e._inside = /(?:\[[^\]]*\]|\\[\[\]]|[^\[\]]|\](?=[^\[]*\]))*/, $e._href = /\s*<?([\s\S]*?)>?(?:\s+['"]([\s\S]*?)['"])?\s*/, $e.link = Ae($e.link)("inside", $e._inside)("href", $e._href)(), $e.reflink = Ae($e.reflink)("inside", $e._inside)(), $e.normal = Ue({}, $e), $e.pedantic = Ue({}, $e.normal, {
    strong: /^__(?=\S)([\s\S]*?\S)__(?!_)|^\*\*(?=\S)([\s\S]*?\S)\*\*(?!\*)/,
    em: /^_(?=\S)([\s\S]*?\S)_(?!_)|^\*(?=\S)([\s\S]*?\S)\*(?!\*)/
}), $e.gfm = Ue({}, $e.normal, {
    escape: Ae($e.escape)("])", "~|])")(),
    url: /^(https?:\/\/[^\s<]+[^<.,:;"')\]\s])/,
    del: /^~~(?=\S)([\s\S]*?\S)~~/,
    text: Ae($e.text)("]|", "~]|")("|", "|https?://|")()
}), $e.breaks = Ue({}, $e.gfm, {
    br: Ae($e.br)("{2,}", "*")(),
    text: Ae($e.gfm.text)("{2,}", "*")()
}), Ne.rules = $e, Ne.output = function(e, t, n) {
    return new Ne(t, n).output(e)
}, Ne.prototype.output = function(e) {
    for (var t, n, r, i, o = ""; e;)
        if (i = this.rules.escape.exec(e)) e = e.substring(i[0].length), o += i[1];
        else if (i = this.rules.autolink.exec(e)) e = e.substring(i[0].length), "@" === i[2] ? (n = Pe(":" === i[1].charAt(6) ? this.mangle(i[1].substring(7)) : this.mangle(i[1])), r = this.mangle("mailto:") + n) : r = n = Pe(i[1]), o += this.renderer.link(r, null, n);
    else if (this.inLink || !(i = this.rules.url.exec(e))) {
        if (i = this.rules.tag.exec(e)) !this.inLink && /^<a /i.test(i[0]) ? this.inLink = !0 : this.inLink && /^<\/a>/i.test(i[0]) && (this.inLink = !1), e = e.substring(i[0].length), o += this.options.sanitize ? this.options.sanitizer ? this.options.sanitizer(i[0]) : Pe(i[0]) : i[0];
        else if (i = this.rules.link.exec(e)) e = e.substring(i[0].length), this.inLink = !0, o += this.outputLink(i, {
            href: i[2],
            title: i[3]
        }), this.inLink = !1;
        else if ((i = this.rules.reflink.exec(e)) || (i = this.rules.nolink.exec(e))) {
            if (e = e.substring(i[0].length), t = (i[2] || i[1]).replace(/\s+/g, " "), !(t = this.links[t.toLowerCase()]) || !t.href) {
                o += i[0].charAt(0), e = i[0].substring(1) + e;
                continue
            }
            this.inLink = !0, o += this.outputLink(i, t), this.inLink = !1
        } else if (i = this.rules.strong.exec(e)) e = e.substring(i[0].length), o += this.renderer.strong(this.output(i[2] || i[1]));
        else if (i = this.rules.em.exec(e)) e = e.substring(i[0].length), o += this.renderer.em(this.output(i[2] || i[1]));
        else if (i = this.rules.code.exec(e)) e = e.substring(i[0].length), o += this.renderer.codespan(Pe(i[2].trim(), !0));
        else if (i = this.rules.br.exec(e)) e = e.substring(i[0].length), o += this.renderer.br();
        else if (i = this.rules.del.exec(e)) e = e.substring(i[0].length), o += this.renderer.del(this.output(i[1]));
        else if (i = this.rules.text.exec(e)) e = e.substring(i[0].length), o += this.renderer.text(Pe(this.smartypants(i[0])));
        else if (e) throw new Error("Infinite loop on byte: " + e.charCodeAt(0))
    } else e = e.substring(i[0].length), r = n = Pe(i[1]), o += this.renderer.link(r, null, n);
    return o
}, Ne.prototype.outputLink = function(e, t) {
    var n = Pe(t.href),
        r = t.title ? Pe(t.title) : null;
    return "!" !== e[0].charAt(0) ? this.renderer.link(n, r, this.output(e[1])) : this.renderer.image(n, r, Pe(e[1]))
}, Ne.prototype.smartypants = function(e) {
    return this.options.smartypants ? e.replace(/---/g, "—").replace(/--/g, "–").replace(/(^|[-\u2014\/(\[{"\s])'/g, "$1‘").replace(/'/g, "’").replace(/(^|[-\u2014\/(\[{\u2018\s])"/g, "$1“").replace(/"/g, "”").replace(/\.{3}/g, "…") : e
}, Ne.prototype.mangle = function(e) {
    if (!this.options.mangle) return e;
    for (var t, n = "", r = e.length, i = 0; i < r; i++) t = e.charCodeAt(i), Math.random() > .5 && (t = "x" + t.toString(16)), n += "&#" + t + ";";
    return n
}, Le.prototype.code = function(e, t, n) {
    if (this.options.highlight) {
        var r = this.options.highlight(e, t);
        null != r && r !== e && (n = !0, e = r)
    }
    return '' 
}, Le.prototype.blockquote = function(e) {
    return "<blockquote>\n" + e + "</blockquote>\n"
}, Le.prototype.html = function(e) {
    return e
}, Le.prototype.heading = function(e, t, n) {
    return "<h" + t + ' id="' + this.options.headerPrefix + n.toLowerCase().replace(/[^\w]+/g, "-") + '">' + e + "</h" + t + ">\n"
}, Le.prototype.hr = function() {
    return this.options.xhtml ? "<hr/>\n" : "<hr>\n"
}, Le.prototype.list = function(e, t) {
    var n = t ? "ol" : "ul";
    return "<" + n + ">\n" + e + "</" + n + ">\n"
}, Le.prototype.listitem = function(e) {
    return "<li>" + e + "</li>\n"
}, Le.prototype.paragraph = function(e) {
    return "<p>" + e + "</p>\n"
}, Le.prototype.table = function(e, t) {
    return "<table>\n<thead>\n" + e + "</thead>\n<tbody>\n" + t + "</tbody>\n</table>\n"
}, Le.prototype.tablerow = function(e) {
    return "<tr>\n" + e + "</tr>\n"
}, Le.prototype.tablecell = function(e, t) {
    var n = t.header ? "th" : "td";
    return (t.align ? "<" + n + ' style="text-align:' + t.align + '">' : "<" + n + ">") + e + "</" + n + ">\n"
}, Le.prototype.strong = function(e) {
    return "<strong>" + e + "</strong>"
}, Le.prototype.em = function(e) {
    return "<em>" + e + "</em>"
}, Le.prototype.codespan = function(e) {
    return "<code>" + e + "</code>"
}, Le.prototype.br = function() {
    return this.options.xhtml ? "<br/>" : "<br>"
}, Le.prototype.del = function(e) {
    return "<del>" + e + "</del>"
}, Le.prototype.link = function(e, t, n) {
    if (this.options.sanitize) {
        try {
            var r = decodeURIComponent(function(e) {
                return e.replace(/&(#(?:\d+)|(?:#x[0-9A-Fa-f]+)|(?:\w+));?/gi, function(e, t) {
                    return "colon" === (t = t.toLowerCase()) ? ":" : "#" === t.charAt(0) ? "x" === t.charAt(1) ? String.fromCharCode(parseInt(t.substring(2), 16)) : String.fromCharCode(+t.substring(1)) : ""
                })
            }(e)).replace(/[^\w:]/g, "").toLowerCase()
        } catch (e) {
            return n
        }
        if (0 === r.indexOf("javascript:") || 0 === r.indexOf("vbscript:") || 0 === r.indexOf("data:")) return n
    }
    this.options.baseUrl && !Oe.test(e) && (e = Me(this.options.baseUrl, e));
    var i = '<a href="' + e + '"';
    return t && (i += ' title="' + t + '"'), i += ">" + n + "</a>"
}, Le.prototype.image = function(e, t, n) {
    this.options.baseUrl && !Oe.test(e) && (e = Me(this.options.baseUrl, e));
    var r = '<img src="' + e + '" alt="' + n + '"';
    return t && (r += ' title="' + t + '"'), r += this.options.xhtml ? "/>" : ">"
}, Le.prototype.text = function(e) {
    return e
}, qe.parse = function(e, t, n) {
    return new qe(t, n).parse(e)
}, qe.prototype.parse = function(e) {
    this.inline = new Ne(e.links, this.options, this.renderer), this.tokens = e.reverse();
    for (var t = ""; this.next();) t += this.tok();
    return t
}, qe.prototype.next = function() {
    return this.token = this.tokens.pop()
}, qe.prototype.peek = function() {
    return this.tokens[this.tokens.length - 1] || 0
}, qe.prototype.parseText = function() {
    for (var e = this.token.text;
        "text" === this.peek().type;) e += "\n" + this.next().text;
    return this.inline.output(e)
}, qe.prototype.tok = function() {
    switch (this.token.type) {
        case "space":
            return "";
        case "hr":
            return this.renderer.hr();
        case "heading":
            return this.renderer.heading(this.inline.output(this.token.text), this.token.depth, this.token.text);
        case "code":
            return this.renderer.code(this.token.text, this.token.lang, this.token.escaped);
        case "table":
            var e, t, n, r, i = "",
                o = "";
            for (n = "", e = 0; e < this.token.header.length; e++)({
                header: !0,
                align: this.token.align[e]
            }), n += this.renderer.tablecell(this.inline.output(this.token.header[e]), {
                header: !0,
                align: this.token.align[e]
            });
            for (i += this.renderer.tablerow(n), e = 0; e < this.token.cells.length; e++) {
                for (t = this.token.cells[e], n = "", r = 0; r < t.length; r++) n += this.renderer.tablecell(this.inline.output(t[r]), {
                    header: !1,
                    align: this.token.align[r]
                });
                o += this.renderer.tablerow(n)
            }
            return this.renderer.table(i, o);
        case "blockquote_start":
            for (o = "";
                "blockquote_end" !== this.next().type;) o += this.tok();
            return this.renderer.blockquote(o);
        case "list_start":
            o = "";
            for (var s = this.token.ordered;
                "list_end" !== this.next().type;) o += this.tok();
            return this.renderer.list(o, s);
        case "list_item_start":
            for (o = "";
                "list_item_end" !== this.next().type;) o += "text" === this.token.type ? this.parseText() : this.tok();
            return this.renderer.listitem(o);
        case "loose_item_start":
            for (o = "";
                "list_item_end" !== this.next().type;) o += this.tok();
            return this.renderer.listitem(o);
        case "html":
            var a = this.token.pre || this.options.pedantic ? this.token.text : this.inline.output(this.token.text);
            return this.renderer.html(a);
        case "paragraph":
            return this.renderer.paragraph(this.inline.output(this.token.text));
        case "text":
            return this.renderer.paragraph(this.parseText())
    }
};
var je = {},
    Oe = /^$|^[a-z][a-z0-9+.-]*:|^[?#]/i;

function Te() {}

function Ue(e) {
    for (var t, n, r = 1; r < arguments.length; r++)
        for (n in t = arguments[r]) Object.prototype.hasOwnProperty.call(t, n) && (e[n] = t[n]);
    return e
}

function Re(e, t, n) {
    if (n || "function" == typeof t) {
        n || (n = t, t = null);
        var r, i, o = (t = Ue({}, Re.defaults, t || {})).highlight,
            s = 0;
        try {
            r = Se.lex(e, t)
        } catch (e) {
            return n(e)
        }
        i = r.length;
        var a = function(e) {
            if (e) return t.highlight = o, n(e);
            var i;
            try {
                i = qe.parse(r, t)
            } catch (t) {
                e = t
            }
            return t.highlight = o, e ? n(e) : n(null, i)
        };
        if (!o || o.length < 3) return a();
        if (delete t.highlight, !i) return a();
        for (; s < r.length; s++) ! function(e) {
            "code" !== e.type ? --i || a() : o(e.text, e.lang, function(t, n) {
                return t ? a(t) : null == n || n === e.text ? --i || a() : (e.text = n, e.escaped = !0, void(--i || a()))
            })
        }(r[s])
    } else try {
        return t && (t = Ue({}, Re.defaults, t)), qe.parse(Se.lex(e, t), t)
    } catch (e) {
        if (e.message += "\nPlease report this to https://github.com/chjj/marked.", (t || Re.defaults).silent) return "<p>An error occurred:</p><pre>" + Pe(e.message + "", !0) + "</pre>";
        throw e
    }
    
}
Te.exec = Te, Re.options = Re.setOptions = function(e) {
    return Ue(Re.defaults, e), Re
}, Re.defaults = {
    gfm: !0,
    tables: !0,
    breaks: !1,
    pedantic: !1,
    sanitize: !1,
    sanitizer: null,
    mangle: !0,
    smartLists: !1,
    silent: !1,
    highlight: null,
    langPrefix: "lang-",
    smartypants: !1,
    headerPrefix: "",
    renderer: new Le,
    xhtml: !1,
    baseUrl: null
}, Re.Parser = qe, Re.parser = qe.parse, Re.Renderer = Le, Re.Lexer = Se, Re.lexer = Se.lex, Re.InlineLexer = Ne, Re.inlineLexer = Ne.output, Re.parse = Re;


function De(e) 
{
	
	
	return function() 
    {
        return ke(function(t) 
        	{
            var n = document.createElement("div");
            n.innerHTML = Re(t, 
            		{
                langPrefix: ""
            		}).trim();
		            var r = n.querySelectorAll("pre code[class]");
		            return r.length > 0 && e(Ie + "highlight.min.js").then(function(t) 
		            		{
		                		r.forEach(function(n) 
		                		{
		                			function r() 
		                			{
		                				t.highlightBlock(n), n.parentNode.classList.add("observablehq--md-pre")
		                			}
		                			t.getLanguage(n.className) ? r() : e(Ie + "async-languages/index.js").then(r => 
		                			{
		                				if (r.has(n.className)) return e(Ie + "async-languages/" + r.get(n.className)).then(e => 
		                				{
		                					t.registerLanguage(n.className, e)
		                					})
		                			}).then(r, r)
		                		})
		            	   }), n
        	}, function() 
        	{
        		return document.createElement("div")
        	})
    }
}

function ze(e) {
    let t;
    Object.defineProperties(this, {
        generator: {
            value: we(e => void(t = e))
        },
        value: {
            get: () => e,
            set: n => t(e = n)
        }
    }), void 0 !== e && t(e)
}

function* Fe() {
    for (;;) yield Date.now()
}
var Be = new Map;

function He(e, t) {
    var n;
    return (n = Be.get(e = +e)) ? n.then(he(t)) : (n = Date.now()) >= e ? Promise.resolve(t) : function(e, t) {
        var n = new Promise(function(n) {
            Be.delete(t);
            var r = t - e;
            if (!(r > 0)) throw new Error("invalid time");
            if (r > 2147483647) throw new Error("too long to wait");
            setTimeout(n, r)
        });
        return Be.set(t, n), n
    }(n, e).then(he(t))
}
var We = {
    delay: function(e, t) {
        return new Promise(function(n) {
            setTimeout(function() {
                n(t)
            }, e)
        })
    },
    tick: function(e, t) {
        return He(Math.ceil((Date.now() + 1) / e) * e, t)
    },
    when: He
};

function Ve(e, t) {
    if (/^(\w+:)|\/\//i.test(e)) return e;
    if (/^[.]{0,2}\//i.test(e)) return new URL(e, null == t ? location : t).href;
    if (!e.length || /^[\s._]/.test(e) || /\s$/.test(e)) throw new Error("illegal name");
    return "https://unpkg.com/" + e
}
const Ge = new Map,
    Ke = [],
    Ye = Ke.map,
    Je = Ke.some,
    Xe = Ke.hasOwnProperty,
    Qe = "https://cdn.jsdelivr.net/npm/",
    Ze = /^((?:@[^\/@]+\/)?[^\/@]+)(?:@([^\/]+))?(?:\/(.*))?$/,
    et = /^\d+\.\d+\.\d+(-[\w-.+]+)?$/,
    tt = /\.[^\/]*$/,
    nt = ["unpkg", "jsdelivr", "browser", "main"];
class RequireError extends Error {
    constructor(e) {
        super(e)
    }
}

function rt(e) {
    const t = Ze.exec(e);
    return t && {
        name: t[1],
        version: t[2],
        path: t[3]
    }
}

function it(e) {
    const t = `${Qe}${e.name}${e.version?`@${e.version}`:""}/package.json`;
    let n = Ge.get(t);
    return n || Ge.set(t, n = fetch(t).then(e => {
        if (!e.ok) throw new RequireError("unable to load package.json");
        return e.redirected && !Ge.has(e.url) && Ge.set(e.url, n), e.json()
    })), n
}
RequireError.prototype.name = RequireError.name;
var ot = st(async function(e, t) {
    if (e.startsWith(Qe) && (e = e.substring(Qe.length)), /^(\w+:)|\/\//i.test(e)) return e;
    if (/^[.]{0,2}\//i.test(e)) return new URL(e, null == t ? location : t).href;
    if (!e.length || /^[\s._]/.test(e) || /\s$/.test(e)) throw new RequireError("illegal name");
    const n = rt(e);
    if (!n) return `${Qe}${e}`;
    if (!n.version && null != t && t.startsWith(Qe)) {
        const e = await it(rt(t.substring(Qe.length)));
        n.version = e.dependencies && e.dependencies[n.name] || e.peerDependencies && e.peerDependencies[n.name]
    }
    if (n.path && !tt.test(n.path) && (n.path += ".js"), n.path && n.version && et.test(n.version)) return `${Qe}${n.name}@${n.version}/${n.path}`;
    const r = await it(n);
    return `${Qe}${r.name}@${r.version}/${n.path||function(e){for(const t of nt){const n=e[t];if("string"==typeof n)return tt.test(n)?n:`${n}.js`}}(r)||"index.js"}`
});

function st(e) {
    const t = new Map,
        n = i(null);

    function r(e) {
        if ("string" != typeof e) return e;
        let n = t.get(e);
        return n || t.set(e, n = new Promise((t, n) => {
            const r = document.createElement("script");
            r.onload = () => {
                try {
                    t(Ke.pop()(i(e)))
                } catch (e) {
                    n(new RequireError("invalid module"))
                }
                r.remove()
            }, r.onerror = () => {
                n(new RequireError("unable to load module")), r.remove()
            }, r.async = !0, r.src = e, window.define = ct, document.head.appendChild(r)
        })), n
    }

    function i(t) {
        return n => Promise.resolve(e(n, t)).then(r)
    }

    function o(e) {
        return arguments.length > 1 ? Promise.all(Ye.call(arguments, n)).then(at) : n(e)
    }
    return o.alias = function(t) {
        return st((n, r) => n in t && (r = null, "string" != typeof(n = t[n])) ? n : e(n, r))
    }, o.resolve = e, o
}

function at(e) {
    const t = {};
    for (const n of e)
        for (const e in n) Xe.call(n, e) && (null == n[e] ? Object.defineProperty(t, e, {
            get: lt(n, e)
        }) : t[e] = n[e]);
    return t
}

function lt(e, t) {
    return () => e[t]
}

function ut(e) {
    return "exports" === (e += "") || "module" === e
}

function ct(e, t, n) {
    const r = arguments.length;
    r < 2 ? (n = e, t = []) : r < 3 && (n = t, t = "string" == typeof e ? [] : e), Ke.push(Je.call(t, ut) ? e => {
        const r = {},
            i = {
                exports: r
            };
        return Promise.all(Ye.call(t, t => "exports" === (t += "") ? r : "module" === t ? i : e(t))).then(e => (n.apply(null, e), i.exports))
    } : e => Promise.all(Ye.call(t, e)).then(e => "function" == typeof n ? n.apply(null, e) : n))
}

function dt(e) {
    return null == e ? ot : st(e)
}
ct.amd = {};
var ht = ke(function(e) {
        var t = document.createElementNS("http://www.w3.org/2000/svg", "g");
        return t.innerHTML = e.trim(), t
    }, function() {
        return document.createElementNS("http://www.w3.org/2000/svg", "g")
    }),
    pt = String.raw;

function ft(e) {
    return new Promise(function(t, n) {
        var r = document.createElement("link");
        r.rel = "stylesheet", r.href = e, r.onerror = n, r.onload = t, document.head.appendChild(r)
    })
}

function mt(e) {
    return function() {
        return Promise.all([e("@observablehq/katex@0.11.1/dist/katex.min.js"), e.resolve("@observablehq/katex@0.11.1/dist/katex.min.css").then(ft)]).then(function(e) {
            var t = e[0],
                n = r();

            function r(e) {
                return function() {
                    var n = document.createElement("div");
                    return t.render(pt.apply(String, arguments), n, e), n.removeChild(n.firstChild)
                }
            }
            return n.options = r, n.block = r({
                displayMode: !0
            }), n
        })
    }
}

function gt() {
    return we(function(e) {
        var t = e(document.body.clientWidth);

        function n() {
            var n = document.body.clientWidth;
            n !== t && e(t = n)
        }
        return window.addEventListener("resize", n),
            function() {
                window.removeEventListener("resize", n)
            }
    })
}

function bt(e) {
    const t = dt(e);
    Object.defineProperties(this, {
        DOM: {
            value: ge,
            writable: !0,
            enumerable: !0
        },
        FileAttachment: {
            value: he(de),
            writable: !0,
            enumerable: !0
        },
        Files: {
            value: be,
            writable: !0,
            enumerable: !0
        },
        Generators: {
            value: xe,
            writable: !0,
            enumerable: !0
        },
        html: {
            value: he(Ee),
            writable: !0,
            enumerable: !0
        },
        md: {
            value: De(t),
            writable: !0,
            enumerable: !0
        },
        Mutable: {
            value: he(ze),
            writable: !0,
            enumerable: !0
        },
        now: {
            value: Fe,
            writable: !0,
            enumerable: !0
        },
        Promises: {
            value: We,
            writable: !0,
            enumerable: !0
        },
        require: {
            value: he(t),
            writable: !0,
            enumerable: !0
        },
        resolve: {
            value: he(Ve),
            writable: !0,
            enumerable: !0
        },
        svg: {
            value: he(ht),
            writable: !0,
            enumerable: !0
        },
        tex: {
            value: mt(t),
            writable: !0,
            enumerable: !0
        },
        width: {
            value: gt,
            writable: !0,
            enumerable: !0
        }
    })
}

function vt(e, t) {
    this.message = e + "", this.input = t
}
vt.prototype = Object.create(Error.prototype), vt.prototype.name = "RuntimeError", vt.prototype.constructor = vt;
var _t = Array.prototype,
    wt = _t.map,
    yt = _t.forEach;

function xt(e) {
    return function() {
        return e
    }
}

function kt(e) {
    return e
}

function Et() {}
var Ct = 1,
    St = 2,
    $t = 3,
    Nt = {};

function Lt(e, t, n) {
    var r;
    null == n && (n = Nt), Object.defineProperties(this, {
        _observer: {
            value: n,
            writable: !0
        },
        _definition: {
            value: At,
            writable: !0
        },
        _duplicate: {
            value: void 0,
            writable: !0
        },
        _duplicates: {
            value: void 0,
            writable: !0
        },
        _indegree: {
            value: NaN,
            writable: !0
        },
        _inputs: {
            value: [],
            writable: !0
        },
        _invalidate: {
            value: Et,
            writable: !0
        },
        _module: {
            value: t
        },
        _name: {
            value: null,
            writable: !0
        },
        _outputs: {
            value: new Set,
            writable: !0
        },
        _promise: {
            value: Promise.resolve(void 0),
            writable: !0
        },
        _reachable: {
            value: n !== Nt,
            writable: !0
        },
        _rejector: {
            value: (r = this, function(e) {
                if (e === At) throw new vt(r._name + " is not defined", r._name);
                throw new vt(r._name + " could not be resolved", r._name)
            })
        },
        _type: {
            value: e
        },
        _value: {
            value: void 0,
            writable: !0
        },
        _version: {
            value: 0,
            writable: !0
        }
    })
}

function qt(e) {
    e._module._runtime._dirty.add(e), e._outputs.add(this)
}

function Pt(e) {
    e._module._runtime._dirty.add(e), e._outputs.delete(this)
}

function At() {
    throw At
}

function Mt(e) {
    return function() {
        throw new vt(e + " is defined more than once")
    }
}

function jt(e, t, n) {
    var r = this._module._scope,
        i = this._module._runtime;
    if (this._inputs.forEach(Pt, this), t.forEach(qt, this), this._inputs = t, this._definition = n, this._value = void 0, n === Et ? i._variables.delete(this) : i._variables.add(this), e == this._name && r.get(e) === this) this._outputs.forEach(i._updates.add, i._updates);
    else {
        var o, s;
        if (this._name)
            if (this._outputs.size) r.delete(this._name), (s = this._module._resolve(this._name))._outputs = this._outputs, this._outputs = new Set, s._outputs.forEach(function(e) {
                e._inputs[e._inputs.indexOf(this)] = s
            }, this), s._outputs.forEach(i._updates.add, i._updates), i._dirty.add(s).add(this), r.set(this._name, s);
            else if ((s = r.get(this._name)) === this) r.delete(this._name);
        else {
            if (s._type !== $t) throw new Error;
            s._duplicates.delete(this), this._duplicate = void 0, 1 === s._duplicates.size && (s = s._duplicates.keys().next().value, o = r.get(this._name), s._outputs = o._outputs, o._outputs = new Set, s._outputs.forEach(function(e) {
                e._inputs[e._inputs.indexOf(o)] = s
            }), s._definition = s._duplicate, s._duplicate = void 0, i._dirty.add(o).add(s), i._updates.add(s), r.set(this._name, s))
        }
        if (this._outputs.size) throw new Error;
        e && ((s = r.get(e)) ? s._type === $t ? (this._definition = Mt(e), this._duplicate = n, s._duplicates.add(this)) : s._type === St ? (this._outputs = s._outputs, s._outputs = new Set, this._outputs.forEach(function(e) {
            e._inputs[e._inputs.indexOf(s)] = this
        }, this), i._dirty.add(s).add(this), r.set(e, this)) : (s._duplicate = s._definition, this._duplicate = n, (o = new Lt($t, this._module))._name = e, o._definition = this._definition = s._definition = Mt(e), o._outputs = s._outputs, s._outputs = new Set, o._outputs.forEach(function(e) {
            e._inputs[e._inputs.indexOf(s)] = o
        }), o._duplicates = new Set([this, s]), i._dirty.add(s).add(o), i._updates.add(s).add(o), r.set(e, o)) : r.set(e, this)), this._name = e
    }
    return i._updates.add(this), i._compute(), this
}

function Ot(e, t = []) {
    Object.defineProperties(this, {
        _runtime: {
            value: e
        },
        _scope: {
            value: new Map
        },
        _builtins: {
            value: new Map([
                ["invalidation", Rt],
                ["visibility", It], ...t
            ])
        },
        _source: {
            value: null,
            writable: !0
        }
    })
}

function Tt(e) {
    return e._name
}
Object.defineProperties(Lt.prototype, {
    _pending: {
        value: function() {
            this._observer.pending && this._observer.pending()
        },
        writable: !0,
        configurable: !0
    },
    _fulfilled: {
        value: function(e) {
            this._observer.fulfilled && this._observer.fulfilled(e, this._name)
        },
        writable: !0,
        configurable: !0
    },
    _rejected: {
        value: function(e) {
            this._observer.rejected && this._observer.rejected(e, this._name)
        },
        writable: !0,
        configurable: !0
    },
    define: {
        value: function(e, t, n) {
            switch (arguments.length) {
                case 1:
                    n = e, e = t = null;
                    break;
                case 2:
                    n = t, "string" == typeof e ? t = null : (t = e, e = null)
            }
            return jt.call(this, null == e ? null : e + "", null == t ? [] : wt.call(t, this._module._resolve, this._module), "function" == typeof n ? n : xt(n))
        },
        writable: !0,
        configurable: !0
    },
    delete: {
        value: function() {
            return jt.call(this, null, [], Et)
        },
        writable: !0,
        configurable: !0
    },
    import: {
        value: function(e, t, n) {
            arguments.length < 3 && (n = t, t = e);
            return jt.call(this, t + "", [n._resolve(e + "")], kt)
        },
        writable: !0,
        configurable: !0
    }
}), Object.defineProperties(Ot.prototype, {
    _copy: {
        value: function(e, t) {
            e._source = this, t.set(this, e);
            for (const [o, s] of this._scope) {
                var n = e._scope.get(o);
                if (!n || n._type !== Ct)
                    if (s._definition === kt) {
                        var r = s._inputs[0],
                            i = r._module;
                        e.import(r._name, o, t.get(i) || (i._source ? i._copy(new Ot(e._runtime, e._builtins), t) : i))
                    } else e.define(o, s._inputs.map(Tt), s._definition)
            }
            return e
        },
        writable: !0,
        configurable: !0
    },
    _resolve: {
        value: function(e) {
            var t, n = this._scope.get(e);
            if (!n)
                if (n = new Lt(St, this), this._builtins.has(e)) n.define(e, xt(this._builtins.get(e)));
                else if (this._runtime._builtin._scope.has(e)) n.import(e, this._runtime._builtin);
            else {
                try {
                    t = this._runtime._global(e)
                } catch (t) {
                    return n.define(e, (r = t, function() {
                        throw r
                    }))
                }
                void 0 === t ? this._scope.set(n._name = e, n) : n.define(e, xt(t))
            }
            var r;
            return n
        },
        writable: !0,
        configurable: !0
    },
    redefine: {
        value: function(e) {
            var t = this._scope.get(e);
            if (!t) throw new vt(e + " is not defined");
            if (t._type === $t) throw new vt(e + " is defined more than once");
            return t.define.apply(t, arguments)
        },
        writable: !0,
        configurable: !0
    },
    define: {
        value: function() {
            var e = new Lt(Ct, this);
            return e.define.apply(e, arguments)
        },
        writable: !0,
        configurable: !0
    },
    derive: {
        value: function(e, t) {
            var n = new Ot(this._runtime, this._builtins);
            return n._source = this, yt.call(e, function(e) {
                "object" != typeof e && (e = {
                    name: e + ""
                }), null == e.alias && (e.alias = e.name), n.import(e.name, e.alias, t)
            }), Promise.resolve().then(() => {
                const e = new Set([this]);
                for (const t of e)
                    for (const n of t._scope.values())
                        if (n._definition === kt) {
                            const t = n._inputs[0]._module,
                                r = t._source || t;
                            if (r === this) return void console.warn("circular module definition; ignoring");
                            e.add(r)
                        } this._copy(n, new Map)
            }), n
        },
        writable: !0,
        configurable: !0
    },
    import: {
        value: function() {
            var e = new Lt(Ct, this);
            return e.import.apply(e, arguments)
        },
        writable: !0,
        configurable: !0
    },
    value: {
        value: async function(e) {
            var t = this._scope.get(e);
            if (!t) throw new vt(e + " is not defined");
            t._observer === Nt && (t._observer = !0, this._runtime._dirty.add(t));
            return await this._runtime._compute(), t._promise
        },
        writable: !0,
        configurable: !0
    },
    variable: {
        value: function(e) {
            return new Lt(Ct, this, e)
        },
        writable: !0,
        configurable: !0
    },
    builtin: {
        value: function(e, t) {
            this._builtins.set(e, t)
        },
        writable: !0,
        configurable: !0
    }
});
const Ut = "function" == typeof requestAnimationFrame ? requestAnimationFrame : setImmediate;
var Rt = {},
    It = {};

function Dt(e = new bt, t = function(e) {
    return window[e]
}) {
    var n = this.module();
    if (Object.defineProperties(this, {
            _dirty: {
                value: new Set
            },
            _updates: {
                value: new Set
            },
            _computing: {
                value: null,
                writable: !0
            },
            _init: {
                value: null,
                writable: !0
            },
            _modules: {
                value: new Map
            },
            _variables: {
                value: new Set
            },
            _disposed: {
                value: !1,
                writable: !0
            },
            _builtin: {
                value: n
            },
            _global: {
                value: t
            }
        }), e)
        for (var r in e) new Lt(St, n).define(r, [], e[r])
}

function zt(e) {
    const t = new Set(e._inputs);
    for (const n of t) {
        if (n === e) return !0;
        n._inputs.forEach(t.add, t)
    }
    return !1
}

function Ft(e) {
    ++e._indegree
}

function Bt(e) {
    --e._indegree
}

function Ht(e) {
    return e._promise.catch(e._rejector)
}

function Wt(e) {
    return new Promise(function(t) {
        e._invalidate = t
    })
}

function Vt(e, t) {
    let n, r, i = "function" == typeof IntersectionObserver && t._observer && t._observer._node,
        o = !i,
        s = Et,
        a = Et;
    return i && ((r = new IntersectionObserver(([e]) => (o = e.isIntersecting) && (n = null, s()))).observe(i), e.then(() => (r.disconnect(), r = null, a()))),
        function(e) {
            return o ? Promise.resolve(e) : r ? (n || (n = new Promise((e, t) => (s = e, a = t))), n.then(() => e)) : Promise.reject()
        }
}

function Gt(e) {
    e._invalidate(), e._invalidate = Et, e._pending();
    var t = e._value,
        n = ++e._version,
        r = null,
        i = e._promise = Promise.all(e._inputs.map(Ht)).then(function(i) {
            if (e._version === n) {
                for (var o = 0, s = i.length; o < s; ++o) switch (i[o]) {
                    case Rt:
                        i[o] = r = Wt(e);
                        break;
                    case It:
                        r || (r = Wt(e)), i[o] = Vt(r, e)
                }
                return e._definition.apply(t, i)
            }
        }).then(function(t) {
            return function(e) {
                return e && "function" == typeof e.next && "function" == typeof e.return
            }(t) ? ((r || Wt(e)).then((o = t, function() {
                o.return()
            })), function(e, t, n, r) {
                function i() {
                    var n = new Promise(function(e) {
                        e(r.next())
                    }).then(function(r) {
                        return r.done ? void 0 : Promise.resolve(r.value).then(function(r) {
                            if (e._version === t) return Kt(e, r, n).then(i), e._fulfilled(r), r
                        })
                    });
                    n.catch(function(r) {
                        e._version === t && (Kt(e, void 0, n), e._rejected(r))
                    })
                }
                return new Promise(function(e) {
                    e(r.next())
                }).then(function(e) {
                    if (!e.done) return n.then(i), e.value
                })
            }(e, n, i, t)) : t;
            var o
        });
    i.then(function(t) {
        e._version === n && (e._value = t, e._fulfilled(t))
    }, function(t) {
        e._version === n && (e._value = void 0, e._rejected(t))
    })
}

function Kt(e, t, n) {
    var r = e._module._runtime;
    return e._value = t, e._promise = n, e._outputs.forEach(r._updates.add, r._updates), r._compute()
}

function Yt(e, t) {
    e._invalidate(), e._invalidate = Et, e._pending(), ++e._version, e._indegree = NaN, (e._promise = Promise.reject(t)).catch(Et), e._value = void 0, e._rejected(t)
}
Object.defineProperties(Dt, {
    load: {
        value: function(e, t, n) {
            if ("function" == typeof t && (n = t, t = null), "function" != typeof n) throw new Error("invalid observer");
            null == t && (t = new bt);
            const {
                modules: r,
                id: i
            } = e, o = new Map, s = new Dt(t), a = l(i);

            function l(e) {
                let t = o.get(e);
                return t || o.set(e, t = s.module()), t
            }
            for (const e of r) {
                const t = l(e.id);
                let r = 0;
                for (const i of e.variables) i.from ? t.import(i.remote, i.name, l(i.from)) : t === a ? t.variable(n(i, r, e.variables)).define(i.name, i.inputs, i.value) : t.define(i.name, i.inputs, i.value), ++r
            }
            return s
        },
        writable: !0,
        configurable: !0
    }
}), Object.defineProperties(Dt.prototype, {
    _compute: {
        value: function() {
            return this._computing || (this._computing = this._computeSoon())
        },
        writable: !0,
        configurable: !0
    },
    _computeSoon: {
        value: function() {
            var e = this;
            return new Promise(function(t) {
                Ut(function() {
                    t(), e._disposed || e._computeNow()
                })
            })
        },
        writable: !0,
        configurable: !0
    },
    _computeNow: {
        value: function() {
            var e, t, n = [];
            (e = new Set(this._dirty)).forEach(function(t) {
                t._inputs.forEach(e.add, e);
                const n = function(e) {
                    if (e._observer !== Nt) return !0;
                    var t = new Set(e._outputs);
                    for (const e of t) {
                        if (e._observer !== Nt) return !0;
                        e._outputs.forEach(t.add, t)
                    }
                    return !1
                }(t);
                n > t._reachable ? this._updates.add(t) : n < t._reachable && t._invalidate(), t._reachable = n
            }, this), (e = new Set(this._updates)).forEach(function(t) {
                t._reachable ? (t._indegree = 0, t._outputs.forEach(e.add, e)) : (t._indegree = NaN, e.delete(t))
            }), this._computing = null, this._updates.clear(), this._dirty.clear(), e.forEach(function(e) {
                e._outputs.forEach(Ft)
            });
            do {
                for (e.forEach(function(e) {
                        0 === e._indegree && n.push(e)
                    }); t = n.pop();) Gt(t), t._outputs.forEach(r), e.delete(t);
                e.forEach(function(t) {
                    zt(t) && (Yt(t, new vt("circular definition")), t._outputs.forEach(Bt), e.delete(t))
                })
            } while (e.size);

            function r(e) {
                0 == --e._indegree && n.push(e)
            }
        },
        writable: !0,
        configurable: !0
    },
    dispose: {
        value: function() {
            this._computing = Promise.resolve(), this._disposed = !0, this._variables.forEach(e => {
                e._invalidate(), e._version = NaN
            })
        },
        writable: !0,
        configurable: !0
    },
    module: {
        value: function(e, t = Et) {
            let n;
            if (void 0 === e) return (n = this._init) ? (this._init = null, n) : new Ot(this);
            if (n = this._modules.get(e)) return n;
            this._init = n = new Ot(this), this._modules.set(e, n);
            try {
                e(this, t)
            } finally {
                this._init = null
            }
            return n
        },
        writable: !0,
        configurable: !0
    },
    fileAttachments: {
        value: function(e) {
            return t => {
                const n = e(t += "");
                if (null == n) throw new Error(`File not found: ${t}`);
                return new FileAttachment(n, t)
            }
        },
        writable: !0,
        configurable: !0
    }
});
export {
    ue as Inspector, bt as Library, Dt as Runtime, vt as RuntimeError
};
